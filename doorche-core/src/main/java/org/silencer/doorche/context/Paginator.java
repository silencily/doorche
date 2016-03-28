package org.silencer.doorche.context;

import java.io.Serializable;

/**
 * @author gejb
 * @since 2016/3/10
 */
public class Paginator implements Serializable {
    /**
     * 当不需要分页时返回这个实例
     */
    public static final Paginator NOT_PAGINATED = new Paginator() {
        public String toString() {
            return "not paginated";
        }
    };
    public static final int DEFAULT_PAGE_SIZE = 2;

    /**
     * 每页行数, 通常在页面中可以修改这个参数
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    /**
     * 当前是第几页, 从<b>0</b>开始计算
     */
    private int page;

    /**
     * 所有的行数
     */
    private int count;

    /**
     * 不执行分页操作的标志, 除非设置了这个实例的分页标志. 否则不应该对这个实例执行分页. <br>
     * 在 架构自动执行分页操作时, 因为这个实例是<code>ConditionModel</code>的一个属性,<br>
     * 当请 求一个<code>controller</code>时, 如果参数中有<code>paginator.page=1</code>等参数,<br>
     * <code>web</code>层就会组装这个实例, 从而使这个属性成为<code>false</code>. 如果没有
     * 明确地设置这个实例的属性架构就不执行分页
     */
    private boolean notPaginated = true;

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.notPaginated = false;
        if (this.pageSize < 1) {
            this.pageSize = DEFAULT_PAGE_SIZE;
        }
    }

    /**
     * 返回一页中表现多少行数据
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * 设置当前实例在第<code>page</code>页
     */
    public void setPage(int page) {
        this.notPaginated = false;
        this.page = page;
    }

    /**
     * 返回当前实例在第几页
     */
    public int getPage() {
        return page;
    }

    /**
     * 设置当前实例共有多少行数据
     */
    public void setCount(int count) {
        this.count = count;
        this.notPaginated = false;
    }

    /**
     * 返回所有的数据行数
     */
    public int getCount() {
        return count;
    }

    /**
     * 返回当前实例总共的页数, 如果没有数据返回<b>0</b>, 如果数据不足一页返回<b>1</b>
     *
     * @return 当前实例总共的页数
     */
    public final int getPageCount() {
        int remainder = count % pageSize;
        if (count != 0 && remainder == count) {
            return 1;
        } else if (remainder == 0) {
            return (count / pageSize);
        } else {
            return (count / pageSize) + 1;
        }
    }

    /**
     * 判断是否是第一页, 如果当前实例没有数据或只有一页返回<code>true</code>
     *
     * @return
     */
    public boolean isFirst() {
        return getPageCount() < 2 || page == 0;
    }

    /**
     * 判断是否是最后一页, 如果当前实例没有数据或只有一页返回<code>true</code>
     *
     * @return 是否是最后一页
     */
    public boolean isLast() {
        return (getPageCount() < 2) || (page == (getPageCount() - 1));
    }

    /**
     * 判断是否存在上一页
     */
    public boolean isPreviousPageAvailable() {
        return getPageCount() > 1 && page > 0;
    }

    /**
     * 判断是否存在下一页
     */
    public boolean isNextPageAvailable() {
        return getPageCount() > 1 && page < getPageCount() - 1;
    }

    public boolean isNotPaginated() {
        return this.notPaginated;
    }
}
