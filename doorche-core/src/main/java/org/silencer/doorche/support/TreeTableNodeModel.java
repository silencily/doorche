/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.support;

/**
 * @author gejb
 * @since 2016-05-10
 */
public class TreeTableNodeModel<T> {
    private boolean checked = false;
    private T node;

    public TreeTableNodeModel(T node) {
        this.node = node;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public T getNode() {
        return node;
    }
}
