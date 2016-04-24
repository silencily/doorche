package org.silencer.doorche.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.silencer.doorche.context.Condition;
import org.silencer.doorche.context.ConditionContext;
import org.silencer.doorche.context.ConditionContextManager;
import org.silencer.doorche.context.Paginator;
import org.silencer.doorche.entity.AbstractEntity;
import org.silencer.doorche.entity.TsmUser;
import org.silencer.doorche.security.SecurityContextHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

/**
 * hibernate数据操作模板对象
 *
 * @author gejb
 * @since 2016/3/9
 */
@Component
public class HibernateTemplate {
    private static final Log logger = LogFactory.getLog(HibernateTemplate.class);

    private static final String CRITERIA_ASSERT_ERROR_MESSAGE = " 's type is not " + CriteriaImpl.class + ", please make sure you are using Hibernate-4.1.6.Final!!! ";

    @Resource
    private SessionFactory sessionFactory;
    @Autowired
    private ConditionContextManager conditionContextManager;

    /**
     * 根据查询条件进行查询结果集
     *
     * @param dc 离线查询条件对象
     * @return 查询结果集
     */
    public List findByCriteria(DetachedCriteria dc) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = dc.getExecutableCriteria(session);

        Paginator paginator = null;
        int firstResult = 0;
        int maxResults = 0;

        //处理查询条件
        if (!conditionContextManager.isConcealQuery()) {
            rebuildCriteria(dc);//重新构建查询

            paginator = conditionContextManager.getConditionContext().getPaginator();
            firstResult = paginator.getPage() * paginator.getPageSize();
            maxResults = paginator.getPageSize();

            conditionContextManager.concealQuery();//重新构建查询后需要屏蔽自动查询条件
        }

        if (paginator != null && !paginator.isNotPaginated()) {
            // Get the orginal orderEntries
            CriteriaImpl.OrderEntry[] orderEntries = getOrders(criteria);
            // Remove the orders
            criteria = removeOrders(criteria);
            // get the original projection
            Projection projection = getProjection(criteria);

            Object objCount = criteria.setProjection(Projections.rowCount()).uniqueResult();
            if (objCount == null) {
                throw new RuntimeException("无法执行 count 统计, 请检查hibernate相应的配置");
            }
            Integer iCount = Integer.parseInt(objCount.toString());
            paginator.setCount(iCount);

            criteria.setProjection(projection);
            if (projection == null) {
                // Set the ResultTransformer to get the same object structure with hql
                criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
            }
            // Add the orginal orderEntries
            criteria = addOrders(criteria, orderEntries);
            // 追加排序项目id(如果不进行排序,翻页处理无法正常显示)
            Order order = Order.asc("id");//new Order("id", true);
            criteria.addOrder(order);
            // 修正查询后查询结果不在第一页无法显示的问题, 自动定向到第一页
            if (firstResult >= iCount) {
                firstResult = 0;
                paginator.setPage(0);
            }

            if (firstResult >= 0) {
                criteria.setFirstResult(firstResult);
            }
            if (maxResults > 0) {
                criteria.setMaxResults(maxResults);
            }
        }
        return criteria.list();

    }

    /**
     * 重新构建查询条件
     *
     * @param detachedCriteria
     * @return
     */
    private DetachedCriteria rebuildCriteria(DetachedCriteria detachedCriteria) {
        ConditionContext conditionContext = conditionContextManager.getConditionContext();
        Condition[] conditions = conditionContext.getConditions();

        for (int i = 0; i < conditions.length; i++) {
            Condition condition = conditions[i];
            if (!condition.isPlace()) {//判断条件是否有效
                continue;
            }
            String name = condition.getName();
            Object value = condition.getValue();
            if (String.class.isInstance(value)) {
                value = ((String) value).trim();
            }

            //处理关联查询
            List<String> aliasList = new ArrayList<String>();
            String lastAlias = null;
            if (name.contains(Condition.PROPERTY_SEPARATOR) && condition.isCreateAlias()) {
                StringTokenizer tokenizer = new StringTokenizer(name, Condition.PROPERTY_SEPARATOR, false);
                StringBuilder createdAlias = new StringBuilder();
                while (tokenizer.hasMoreTokens()) {
                    String alias = tokenizer.nextToken();
                    if (tokenizer.hasMoreTokens()) {
                        //可能包含多级关联
                        lastAlias = alias;
                        createdAlias.append(alias);
                        String associationPath = createdAlias.toString();
                        createdAlias.append(Condition.PROPERTY_SEPARATOR);

                        if (!aliasList.contains(associationPath)) {
                            detachedCriteria.createAlias(associationPath, alias);
                            aliasList.add(associationPath);
                        }
                    }
                }
            }
            String propertyName = name;
            if (lastAlias != null) { //包含关联查询propertyName为"b.c"
                propertyName = lastAlias + name.substring(name.lastIndexOf(Condition.PROPERTY_SEPARATOR));
            }
            //判断操作
//            if(i!=0){
//                String prepend = condition.getPrepend();//与前一个属性的关系"and"或"or"
//            }
            //根据操作类型构建查询
            switch (condition.determineOperator()) {
                case EQUAL:
                    detachedCriteria.add(Restrictions.eq(propertyName, value));
                    break;
                case NOT_EQUAL:
                    detachedCriteria.add(Restrictions.ne(propertyName, value));
                    break;
                case GREATER_EQUAL:
                    detachedCriteria.add(Restrictions.ge(propertyName, value));
                    break;
                case GREATER:
                    detachedCriteria.add(Restrictions.gt(propertyName, value));
                    break;
                case LESS_EQUAL:
                    detachedCriteria.add(Restrictions.le(propertyName, value));
                    break;
                case LESS:
                    detachedCriteria.add(Restrictions.lt(propertyName, value));
                    break;
                case LIKE:
                    detachedCriteria.add(Restrictions.like(propertyName, "%" + value + "%"));
                    break;
            }
        }

        return detachedCriteria;
    }

    /**
     * 从 criteria 中取得 {@link org.hibernate.criterion.Projection}, 接口中没有公开此方法, 因此从 {@link CriteriaImpl} 中取得
     *
     * @param criteria the criteria
     * @return the Projection
     * @see CriteriaImpl#getProjection()
     */
    private Projection getProjection(Criteria criteria) {
        assertType(criteria);
        CriteriaImpl impl = (CriteriaImpl) criteria;
        return impl.getProjection();
    }

    private void assertType(Criteria criteria) {
        Assert.notNull(criteria, " criteria is required. ");
        String message = criteria + CRITERIA_ASSERT_ERROR_MESSAGE;
        if (!CriteriaImpl.class.isInstance(criteria)) {
            logger.error(message);
            throw new RuntimeException(message);
        }
    }

    /**
     * 得到 criteria 中的 OrderEntry[]
     *
     * @param criteria the criteria
     * @return the OrderEntry[]
     */
    private CriteriaImpl.OrderEntry[] getOrders(Criteria criteria) {
        assertType(criteria);
        CriteriaImpl impl = (CriteriaImpl) criteria;
        Field field = getOrderEntriesField(criteria);
        try {
            return (CriteriaImpl.OrderEntry[]) ((List) field.get(impl)).toArray(new CriteriaImpl.OrderEntry[0]);
        } catch (Exception e) {
            String message = criteria + CRITERIA_ASSERT_ERROR_MESSAGE;
            logger.error(message, e);
            throw new RuntimeException(message, e);
        }
    }

    /**
     * 移除 criteria 中的 OrderEntry[]
     *
     * @param criteria the criteria
     * @return the criteria after removed OrderEntry[]
     */
    private Criteria removeOrders(Criteria criteria) {
        assertType(criteria);
        CriteriaImpl impl = (CriteriaImpl) criteria;

        try {
            Field field = getOrderEntriesField(criteria);
            field.set(impl, new ArrayList());
            return impl;
        } catch (Exception e) {
            String message = criteria + CRITERIA_ASSERT_ERROR_MESSAGE;
            logger.error(message, e);
            throw new RuntimeException(message, e);
        }
    }

    /**
     * 为 criteria 增加 OrderEntry[]
     *
     * @param criteria     the criteria
     * @param orderEntries the OrderEntry[]
     * @return the criteria after add OrderEntry[]
     */
    private Criteria addOrders(Criteria criteria, CriteriaImpl.OrderEntry[] orderEntries) {
        assertType(criteria);
        CriteriaImpl impl = (CriteriaImpl) criteria;
        try {
            Field field = getOrderEntriesField(criteria);
            for (int i = 0; i < orderEntries.length; i++) {
                List innerOrderEntries = (List) field.get(criteria);
                innerOrderEntries.add(orderEntries[i]);
            }
            return impl;
        } catch (Exception e) {
            String message = criteria + CRITERIA_ASSERT_ERROR_MESSAGE;
            logger.error(message, e);
            throw new RuntimeException(message, e);
        }
    }

    private Field getOrderEntriesField(Criteria criteria) {
        assertType(criteria);
        try {
            Field field = CriteriaImpl.class.getDeclaredField("orderEntries");
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            String message = criteria + CRITERIA_ASSERT_ERROR_MESSAGE;
            logger.error(message, e);
            throw new RuntimeException(message, e);
        }
    }

    /**
     * 懒加载实体对象，使用代理创建,实体对象必须在数据库中存在
     *
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public <T extends AbstractEntity> T load(Class<T> clazz, Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Object entity = session.load(clazz, id);
        return (T) entity;
    }

    /**
     * 获取实体对象，若数据库中不存则返回null
     *
     * @param clazz
     * @param id
     * @param <T>
     * @return
     */
    public <T extends AbstractEntity> T get(Class<T> clazz, Integer id) {
        Session session = sessionFactory.getCurrentSession();
        Object entity = session.get(clazz, id);
        return (T) entity;
    }


    /**
     * 更新保存
     *
     * @param entity
     */
    public void update(AbstractEntity entity) {
        Session session = sessionFactory.getCurrentSession();
        entity.setUpdateTime(new Date());
        int currentUserId = SecurityContextHelper.obtainCurrentSecurityUserId();
        TsmUser tsmUser = (TsmUser) session.load(TsmUser.class, currentUserId);
        entity.setUpdateBy(tsmUser);

        session.update(entity);
    }

    /**
     * 新增保存
     *
     * @param entity
     */
    public void save(AbstractEntity entity) {
        Session session = sessionFactory.getCurrentSession();
        entity.setCreateTime(new Date());
        int currentUserId = SecurityContextHelper.obtainCurrentSecurityUserId();
        TsmUser tsmUser = (TsmUser) session.load(TsmUser.class, currentUserId);
        entity.setCreateBy(tsmUser);
        session.save(entity);
    }

    /**
     * 如果对象已经在本session中持久化了，不做任何事<br>
     * 如果另一个与本session关联的对象拥有相同的持久化标识(identifier)，抛出一个异常<br>
     * 如果对象没有持久化标识(identifier)属性，对其调用save()<br>
     * 如果对象的持久标识(identifier)表明其是一个新实例化的对象，对其调用save()<br>
     * 如果对象是附带版本信息的（通过<version>或<timestamp>） 并且版本属性的值表明其是一个新实例化的对象，save()它。<br>
     * 否则update() 这个对象
     *
     * @param entity 实体对象
     */
    public void saveOrUpdate(AbstractEntity entity) {
        Session session = sessionFactory.getCurrentSession();
        int currentUserId = SecurityContextHelper.obtainCurrentSecurityUserId();
        TsmUser tsmUser = (TsmUser) session.get(TsmUser.class, currentUserId);
        if (entity.getId() == null) {
            entity.setCreateTime(new Date());
            entity.setCreateBy(tsmUser);
        } else {
            entity.setUpdateTime(new Date());
            entity.setUpdateBy(tsmUser);
        }

        session.saveOrUpdate(entity);
    }

    /**
     * merge实体，若包含实体id则update若不包含save
     *
     * @param entity 实体对象
     */
    public void merge(AbstractEntity entity) {
        Session session = sessionFactory.getCurrentSession();
        int currentUserId = SecurityContextHelper.obtainCurrentSecurityUserId();
        TsmUser tsmUser = (TsmUser) session.get(TsmUser.class, currentUserId);
        if (entity.getId() == null) {
            entity.setUpdateTime(new Date());
            entity.setCreateBy(tsmUser);
        } else {
            entity.setUpdateTime(new Date());
            entity.setUpdateBy(tsmUser);
        }
        session.merge(entity);
    }


}
