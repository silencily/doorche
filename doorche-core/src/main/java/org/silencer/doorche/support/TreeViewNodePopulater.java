/* 
 * CopyRright (c) 2014, org.silencer and/or its affiliates. All rights reserved.
 */
package org.silencer.doorche.support;

import java.util.ArrayList;
import java.util.List;

/**
 * 组装树节点结构
 *
 * @author gejb
 * @since 2016-05-07
 */
public class TreeViewNodePopulater {

    /**
     * 组装构建节点树
     *
     * @param dataSrc 扁平树列表
     * @return 节点树
     */
    public static List<TreeViewNode> populate(List<TreeViewNode> dataSrc) {
        TreeViewNode root = new TreeViewNode();
        root.setId(TreeViewNode.ROOT_ID);
        populateHierarchyTree(dataSrc, root);
        return root.getNodes();
    }

    /**
     * 递归实现构建节点树
     *
     * @param dataSrc 数据源
     * @param parent  父节点
     */
    private static void populateHierarchyTree(List<TreeViewNode> dataSrc, TreeViewNode parent) {
        List<TreeViewNode> children = searchChildNodes(dataSrc, parent);
        if (children.size() > 0) {
            parent.setNodes(children);
            for (int i = 0; i < children.size(); i++) {
                TreeViewNode child = children.get(i);
                populateHierarchyTree(dataSrc, child);
            }
        }
    }

    /**
     * 在提供的数据源中查找相应的孩子节点
     *
     * @param dataSrc 数据源
     * @param parent  父节点
     * @return 孩子节点
     */
    private static List<TreeViewNode> searchChildNodes(List<TreeViewNode> dataSrc, TreeViewNode parent) {

        List<TreeViewNode> children = new ArrayList<TreeViewNode>();
        for (int i = 0; i < dataSrc.size(); i++) {
            TreeViewNode child = dataSrc.get(i);
            Integer parentId = parent.getId();
            Integer childParentId = child.getParentId();
            if (parentId.equals(childParentId)) {
                children.add(child);
                //将找到的孩子节点剔除出数据源，并将当前循环索引减1
                dataSrc.remove(i);
                i--;
            }
        }
        return children;
    }

}
