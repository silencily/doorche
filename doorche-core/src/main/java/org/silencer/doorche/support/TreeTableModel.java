/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.support;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gejb
 * @since 2016-05-10
 */
public class TreeTableModel<T> {
    private List<T> srcNodes;
    private Map<Object, T> checkedNodeIds = new HashMap<Object, T>();
    private String checkedMethod;//检查是否选中的方法名


    public TreeTableModel(List<T> srcNodes) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        this(srcNodes, null, null);
    }

    public TreeTableModel(List<T> srcNodes, List<T> checkedNodes, String checkedMethod) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        this.srcNodes = srcNodes;
        if (checkedNodes != null && checkedMethod != null) {
            this.checkedMethod = checkedMethod;
            for (int i = 0; i < checkedNodes.size(); i++) {
                T treeNode = checkedNodes.get(i);
                Object key = MethodUtils.invokeMethod(treeNode,checkedMethod);
                checkedNodeIds.put(key, treeNode);
            }
        }
    }

    public List<TreeTableNodeModel<T>> getNodes() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<TreeTableNodeModel<T>> nodes = new ArrayList<TreeTableNodeModel<T>>();
        for (int i = 0; i < srcNodes.size(); i++) {
            T srcNode = srcNodes.get(i);
            TreeTableNodeModel<T> node = new TreeTableNodeModel<T>(srcNode);
            if(this.checkedMethod!=null){
                Object key = MethodUtils.invokeMethod(srcNode,checkedMethod);
                if(checkedNodeIds.containsKey(key)){
                    node.setChecked(true);
                }
            }
            nodes.add(node);
        }
        return nodes;
    }
}
