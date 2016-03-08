package org.silencer.doorche.context;

/**
 * 条件上下文管理器
 * @author gejb
 * @since 2016/3/8
 */
public interface ConditionContextManager {

    /**
     * 获取条件上下文
     * @return 条件上下文
     */
    public ConditionContext getConditionContext();
}
