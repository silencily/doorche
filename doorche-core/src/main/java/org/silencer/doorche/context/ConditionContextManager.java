package org.silencer.doorche.context;

/**
 * 条件上下文管理器
 *
 * @author gejb
 * @since 2016/3/8
 */
public interface ConditionContextManager {

    /**
     * 获取条件上下文
     *
     * @return 条件上下文
     */
    public ConditionContext getConditionContext();

    /**
     * 设置条件上下文
     *
     * @param context
     */
    public void setConditionContext(ConditionContext context);

    /**
     * 查询当前执行线程是否屏蔽掉了应用于<code>DAO</code>操作的条件, 所有的针对查询操作的
     * <code>AOP</code>组件在应用方法前都要查询这个标志, 以明确是否应用条件.如果当前执行线
     * 程没有条件信息, 什么也不做
     *
     * @return 是否屏蔽掉了查询条件, 如果返回<code>true</code>, 就不要应用这些条件
     */
    public boolean isConcealQuery();

    /**
     * 屏蔽掉当前的条件, 避免在<code>AOP</code>组件中使用. 所有需要修改条件的组件都要查询
     * 这个标志, 以决定是否应用这些条件. 安全中的条件应用不适用于这个规则. 如果当前执行线程
     * 没有条件信息, 什么也不做
     */
    public void concealQuery();

    /**
     * 如果当前执行线程屏蔽了条件信息, 调用这个方法再次恢复条件信息. 当执行<code>DAO</code>
     * 查询时仍要应用这个条件. 如果当前执行线程没有条件信息就什么也不做
     */
    public void recoverQuery();

}
