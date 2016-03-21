package org.silencer.doorche.support;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.silencer.doorche.context.Condition;
import org.silencer.doorche.context.ConditionContext;
import org.silencer.doorche.context.ConditionContextManager;
import org.silencer.doorche.context.Paginator;
import org.silencer.doorche.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
    @Autowired
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

    public <T> List<T> findByClass(Class<T> clazz) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        return (List<T>) findByCriteria(dc);
    }


    /**
     * 更新保存
     *
     * @param entity
     */
    public void update(AbstractEntity entity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(entity);
    }

    /**
     * 新增保存
     *
     * @param entity
     */
    public void save(AbstractEntity entity) {
        Session session = sessionFactory.getCurrentSession();
        session.save(entity);
    }


}
