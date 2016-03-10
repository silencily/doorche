package org.silencer.doorche.context;

import java.io.Serializable;

/**
 * @author gejb
 * @since 2016/3/10
 */
public class Paginater implements Serializable {
    /** 当不需要分页时返回这个实例 */
    public static final Paginater NOT_PAGINATED = new Paginater() {
        public String toString() {
            return "not paginated";
        }
    };
    public static final int DEFAULT_PAGE_SIZE = 14;

    /** 每页行数, 通常在页面中可以修改这个参数 */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /** 当前是第几页, 从<b>0</b>开始计算 */
    private int page;

    /** 所有的行数 */
    private int count;

    /**
     * 不执行分页操作的标志, 除非设置了这个实例的分页标志. 否则不应该对这个实例执行分页. 在 架构自动执行分页操作时, 因为这个实例是<code>BaseActionForm</code>的一个属性,
     * 当请 求一个<code>action</code>时, 如果参数中有<code>paginater.page=1</code>等参数,
     * <code>web</code>层就会组装这个实例, 从而使这个属性成为<code>false</code>. 如果没有
     * 明确地设置这个实例的属性架构就不执行分页
     */
    private boolean notPaginated = true;
}
