package org.silencer.doorche.context;

/**
 * 条件上下文
 * @author gejb
 * @since 2016/3/8
 */
public class ConditionContext {

    private Condition[] conditions;

    private Paginater paginater;

    /**
     * 是否在当前执行环境下禁止应用检索条件, 常用于在持久化层检索多个业务实体时避免一个业务实体
     * 的条件干扰另一个业务实体的检索, 缺省情况下禁止应用这个条件, 否费显式激活
     */
    private boolean concealQuery = true;

    /**
     * 当前查询条件是否为空
     */
    private boolean empty = true;


}
