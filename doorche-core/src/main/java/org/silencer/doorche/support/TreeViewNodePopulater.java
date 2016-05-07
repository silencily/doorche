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


    public static List<TreeViewNode> populate(List<TreeViewNode> dataSrc) {
        TreeViewNode root = new TreeViewNode();
        root.setId(TreeViewNode.ROOT_ID);
        populateHierarchyTree(dataSrc, root);
        return root.getNodes();
    }

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

    private static List<TreeViewNode> searchChildNodes(List<TreeViewNode> dataSrc, TreeViewNode parent) {

        List<TreeViewNode> children = new ArrayList<TreeViewNode>();
        for (int i = 0; i < dataSrc.size(); i++) {
            TreeViewNode child = dataSrc.get(i);
            Integer parentId = parent.getId();
            Integer childParentId = child.getParentId();
            if (parentId.equals(childParentId)) {
                children.add(child);
                dataSrc.remove(i);
                i--;
            }
        }
        return children;
    }

}
