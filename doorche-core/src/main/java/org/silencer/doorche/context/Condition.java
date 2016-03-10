package org.silencer.doorche.context;

import java.io.Serializable;

/**
 * @author gejb
 * @since 2016/3/10
 */
public class Condition implements Serializable, Comparable<Condition> {
    /**
     * 作为检索条件的<code>domain object</code>的属性名称
     */
    private String name;

    /**
     * 属性要满足的值
     */
    private Object value;

    /**
     * 属性与值之间的关系, 有"=", ">", "<"等, 基本遵循<code>SQL</code>的标准
     */
    private String operator;

    /**
     * 属性的类型
     */
    private Class type;

    /**
     * 如果存在多个属性时这个属性与前一个的关系, 这些属性的第一个不解释这个值
     */
    private String prepend;

    /**
     * 如果存在多个条件, 这个属性决定了组成条件表达式时各个条件的顺序
     */
    private int order;

    /**
     * 这个条件是否被列入执行查询的条件中, 比如按照目前的统一规范, 不输入值的条件不作为查询
     * 条件, 对于某些情况, 比如查询人员时条件的值是人员<code>id</code>, 但还有姓名作为显示,
     * 在这种情况下保存人员姓名的<code>condition</code>是不作为查询条件的. 如果没有指定这个
     * 值, 缺省值是<code>true</code>, 说明要考虑这个条件. 这个属性与<code>ignoreEmpty</code>
     * 显著区别是这个条件的值不是空, 但在执行查询时仍然要忽略这个条件, 这个属性实际上更多地为表现
     * 层作了支持
     */
    private boolean place = true;

    /**
     * 如果这个条件的值是<code>null</code>或对于字符串类型是<code>empty</code>在组装查
     * 询条件时是否忽略这个条件. 对于大多数情况是<code>true</code>, 就是当一个条件没有值
     * 时忽略这个条件. 典型的情形是在<code>UI</code>中的搜索条件, 如果没有填写任何值就不
     * 考虑这个条件
     */
    private boolean ignoreEmpty = true;

    /**
     * 当属性名中出现 "." 时是否创建别名, 在两种情况下可能出现 "."
     * <ul>
     * <li>此属性是一个关联属性, 此时 createAlias 应该为 true, 这也是默认情况</li>
     * <li>此属性是一个复合类型, 即 org.hibernate.usertype.CompositeUserType 的实现,  此时 createAlias 应该为 false</li>
     * </ul>
     */
    private boolean createAlias = true;

    /**
     * 相关的一组条件, 比如组成<code>... and (cond1 or cond2)</code>
     */
    private Condition[] compositeConditions;

    @Override
    public int compareTo(Condition o) {
        return 0;
    }
}
