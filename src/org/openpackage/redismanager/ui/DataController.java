/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager.ui;

import java.util.Collection;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.openpackage.redismanager.RedisKey;
import org.openpackage.redismanager.RedisKeys;

/**
 *
 * @author micfans
 */
public class DataController {

    public DefaultTreeModel loadKeys(RedisKeys redisKeys, String filter) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(redisKeys);
        renderKeys(root, redisKeys, filter);
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        return treeModel;
    }

    private void renderKeys(DefaultMutableTreeNode root, RedisKeys redisKeys, String filter) {
        Collection<String> list = redisKeys.getKeys(filter);
        if (list != null) {
            list.stream().map((key) -> {
                RedisKey rk = new RedisKey(key, redisKeys);
                DefaultMutableTreeNode sub = new DefaultMutableTreeNode(rk);
                return sub;
            }).forEachOrdered((sub) -> {
                root.add(sub);
            });
        }
    }
}
