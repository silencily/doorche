package org.silencer.doorche.support;

import org.silencer.doorche.context.Condition;
import org.silencer.doorche.context.Paginator;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 条件上下文表现层model
 *
 * @author gejb
 * @since 2016/3/11
 */
public class ConditionsModel {
    private Map<String, Condition> conditions = new LinkedHashMap<String, Condition>(30);
    private Paginator paginator = new Paginator();

    /**
     * 是否重用查询
     */
    private boolean recondition;

    public Map<String, Condition> getConditions() {
        return conditions;
    }

    public void setConditions(Map<String, Condition> conditions) {
        this.conditions = conditions;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

    public boolean isRecondition() {
        return recondition;
    }

    public void setRecondition(boolean recondition) {
        this.recondition = recondition;
    }
}
