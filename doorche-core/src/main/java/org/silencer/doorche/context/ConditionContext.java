package org.silencer.doorche.context;

/**
 * 条件上下文
 *
 * @author gejb
 * @since 2016/3/8
 */
public class ConditionContext {

    private Condition[] conditions = new Condition[0];

    private Paginator paginator;

    /**
     * 是否在当前执行环境下禁止应用检索条件, 常用于在持久化层检索多个业务实体时避免一个业务实体
     * 的条件干扰另一个业务实体的检索, 缺省情况下禁止应用这个条件, 否费显式激活
     */
    private boolean concealQuery = true;

    /**
     * 当前查询条件是否为空
     */
    private boolean empty = true;

    /**
     * 添加查询条件
     *
     * @param condition 条件
     */
    public void addCondition(Condition condition) {
        if (condition != null) {
            Condition[] newConditions = new Condition[this.conditions.length + 1];
            System.arraycopy(this.conditions, 0, newConditions, 0, this.conditions.length);
            newConditions[newConditions.length - 1] = condition;
            this.conditions = newConditions;
            empty = false;
        }
    }

    public Paginator getPaginator() {
        if (paginator == null) {
            return Paginator.NOT_PAGINATED;
        }
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
        empty = false;
    }

    /**
     * 这个条件的容器是否是<code>empty</code>,就是没有设置过任何条件, 仅仅是标志从未设置过
     * 条件, 而不是说有条件
     *
     * @return 是否设置过条件, 如果这个条件的容器是一个新鲜的实例, 从未设置过条件返回<code>true</code>
     * @see {@link #empty}注释
     */
    public boolean isEmpty() {
        return empty;
    }

    /**
     * 是否禁止应用这个检索条件
     *
     * @return 如果禁止应用条件返回<code>true</code>, 缺省情况下是禁止应用这个条件
     */
    public boolean isConcealQuery() {
        return concealQuery;
    }

    /**
     * 查询时应用或禁止这个条件
     *
     * @param concealQuery <code>true</code>禁止应用条件, <code>false</code>查询时不要禁止这个条件
     */
    public void setConcealQuery(boolean concealQuery) {
        this.concealQuery = concealQuery;
    }
}
