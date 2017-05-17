package org.silencer.doorche.context;

import org.springframework.stereotype.Component;

/**
 * 本地线程条件上下文管理器
 *
 * @author gejb
 * @since 2016/3/8
 */
@Component
public class LocalConditionContextManager implements ConditionContextManager {

    private static final ThreadLocal<ConditionContext> conditionContext = new ThreadLocal<ConditionContext>();

    public ConditionContext getConditionContext() {
        ConditionContext cc = conditionContext.get();
        if (cc == null) {
            cc = new ConditionContext();
            conditionContext.set(cc);
        }
        return cc;
    }

    public void setConditionContext(ConditionContext context) {
        conditionContext.set(context);
    }

    public boolean isConcealQuery() {
        return getConditionContext().isConcealQuery();
    }

    public void concealQuery() {
        getConditionContext().setConcealQuery(true);

    }

    public void recoverQuery() {
        getConditionContext().setConcealQuery(false);
    }

}
