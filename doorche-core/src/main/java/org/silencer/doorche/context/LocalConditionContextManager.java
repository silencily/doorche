package org.silencer.doorche.context;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 本地线程条件上下文管理器
 *
 * @author gejb
 * @since 2016/3/8
 */
@Component
@Lazy(false)
public class LocalConditionContextManager implements ConditionContextManager {

    private static final ThreadLocal<ConditionContext> conditionContext = new ThreadLocal<ConditionContext>();

    @Override
    public ConditionContext getConditionContext() {
        if (conditionContext.get() == null) {
            return null;
        }
        return conditionContext.get();
    }
}
