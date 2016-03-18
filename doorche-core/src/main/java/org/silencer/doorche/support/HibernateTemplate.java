package org.silencer.doorche.support;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.silencer.doorche.context.Condition;
import org.silencer.doorche.context.ConditionContext;
import org.silencer.doorche.context.ConditionContextManager;
import org.silencer.doorche.context.Paginator;
import org.silencer.doorche.entity.AbstractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
     * @param detachedCriteria
     */
    private void rebuildCriteria(DetachedCriteria detachedCriteria) {
        ConditionContext conditionContext = conditionContextManager.getConditionContext();
        Condition[] conditions = conditionContext.getConditions();

        // 记录构造查询条件中的所有 alias
        List aliasList = new ArrayList();

        for (int i = 0; i < conditions.length; i++) {
            Condition condition = conditions[i];
            if (!condition.isPlace()) {
                continue;
            }
            String name = condition.getName();
            String operator = condition.getOperator();
            if (operator == null) {
                operator = Condition.EQUAL;
            }
            Object value = condition.getValue();
            if (String.class.isInstance(value)) {
                value = ((String) value).trim();
            }
            if (Condition.LIKE.equals(operator)) {
                value = "%" + value + "%";
            }
            operator = " " + operator + " ";


        }


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
