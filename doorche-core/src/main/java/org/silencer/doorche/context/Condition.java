package org.silencer.doorche.context;


import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author gejb
 * @since 2016/3/10
 */
public class Condition implements Serializable, Comparable<Condition> {
    /**
     * 表示属性等于值
     */
    public static final String EQUAL = "=";

    /**
     * 表示属性不等于值
     */
    public static final String NOT_EQUAL = "!=";

    /**
     * 表示属性大于或等于值
     */
    public static final String GREATER_EQUAL = ">=";

    /**
     * 表示属性大于值
     */
    public static final String GREATER = ">";

    /**
     * 表示属性小于或等于值
     */
    public static final String LESS_EQUAL = "<=";

    /**
     * 表示属性小于值
     */
    public static final String LESS = "<";

    /**
     * 表示属性满足匹配的值
     */
    public static final String LIKE = "like";

    /**
     * 表示<code>sql</code>语句中的<code>in</code>关系操作符
     */
    public static final String IN = "in";

    /**
     * 表示属性之间的"and"关系
     */
    public static final String AND = "and";

    /**
     * 表示属性之间的"or"关系
     */
    public static final String OR = "or";

    /**
     * 嵌套属性之间的分隔符
     */
    public static final String PROPERTY_SEPARATOR = ".";

    /** 表示属性之间的"not"关系 */
    /*
    String NOT = "not";
    */

    /**
     * 当不指定属性与值之间的操作符时使用的缺省操作符
     */
    public static final String DEFAULT_OPERATOR = EQUAL;

    /**
     * 当不指定属性之间的关系时使用这个缺省的关系
     */
    public static final String DEFAULT_RELATION = AND;

    /**
     * 当不指定条件的类型时缺省的条件值类型
     */
    public static final Class DEFAULT_TYPE = String.class;

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
    //private String prepend;

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
     * </ul>
     */
    private boolean createAlias = true;

    /**
     * 相关的一组条件, 比如组成<code>... and (cond1 or cond2)</code>
     */
    //private Condition[] compositeConditions;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

//    public String getPrepend() {
//        return prepend;
//    }
//
//    public void setPrepend(String prepend) {
//        this.prepend = prepend;
//    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    /**
     * <p>执行查询时是否考虑这个条件, 如果明确设置了<code>false</code>或者这个条件的值是空
     * 并且<code>ignoreEmpty</code>返回<code>true</code>,执行查询时就不考虑这个条件</p>
     * <p>当执行查询时这个方法作为是否考虑这个条件的唯一标准</p>
     *
     * @return 执行查询时是否考虑这个条件
     */
    public boolean isPlace() {
        if (place) {
            /* value 不是空值或 value 是空而且不忽略空值 */
            return (isEmpty() && !ignoreEmpty || !isEmpty());
        } else {
            /* 明确指定了不作为查询条件 */
            return false;
        }
    }

    public void setPlace(boolean place) {
        this.place = place;
    }

    public boolean isIgnoreEmpty() {
        return ignoreEmpty;
    }

    public void setIgnoreEmpty(boolean ignoreEmpty) {
        this.ignoreEmpty = ignoreEmpty;
    }

    public boolean isCreateAlias() {
        return createAlias;
    }

    public void setCreateAlias(boolean createAlias) {
        this.createAlias = createAlias;
    }

//    public Condition[] getCompositeConditions() {
//        return compositeConditions;
//    }
//
//    public void setCompositeConditions(Condition[] compositeConditions) {
//        this.compositeConditions = compositeConditions;
//    }

    private boolean isEmpty() {
        if (value == null) {
            return true;
        } else if (value instanceof String) {
            String str = (String) value;
            return StringUtils.isBlank(str);
        } else {
            return false;
        }
    }

    public int compareTo(Condition o) {
        if (o == null) {
            throw new NullPointerException("比较Condition顺序时,参数是null");
        }
        int ret = 0;
        if (this.equals(o)) {
            ret = 0;
        } else {
            ret = this.order - o.order;
            /* 这里忽略了 ret == 0 的情况, 因为在 ret == 0 时, 与 equals 方法不一致 */
            ret = (ret > 0) ? 1 : -1;
        }
        return ret;
    }

    /**
     * 判断操作类型
     *
     * @return 操作类型
     */
    public Operator determineOperator() {
        Operator operator1 = Operator.EQUAL;
        if (NOT_EQUAL.equals(this.operator)) {
            operator1 = Operator.NOT_EQUAL;
        }
        if (GREATER_EQUAL.equals(this.operator)) {
            operator1 = Operator.GREATER_EQUAL;
        }
        if (GREATER.equals(this.operator)) {
            operator1 = Operator.GREATER;
        }
        if (LESS_EQUAL.equals(this.operator)) {
            operator1 = Operator.LESS_EQUAL;
        }
        if (LESS.equals(this.operator)) {
            operator1 = Operator.LESS;
        }
        if (LIKE.equals(this.operator)) {
            operator1 = Operator.LIKE;
        }
        return operator1;

    }


    /**
     * 条件操作符号
     */
    public enum Operator {
        EQUAL, NOT_EQUAL, GREATER_EQUAL, GREATER, LESS_EQUAL, LESS, LIKE
    }
}
