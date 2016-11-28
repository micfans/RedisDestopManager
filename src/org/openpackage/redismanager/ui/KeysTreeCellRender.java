/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager.ui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.openpackage.redismanager.RedisKey;

/**
 *
 * @author micfans
 */
public class KeysTreeCellRender extends DefaultTreeCellRenderer {

    private static final ImageIcon KEY_T = new ImageIcon(ConnectionTreeCellRender.class.getResource("/org/openpackage/redismanager/ui/assets/x16/T.png"));
    private static final ImageIcon KEY_S = new ImageIcon(ConnectionTreeCellRender.class.getResource("/org/openpackage/redismanager/ui/assets/x16/S.png"));
    private static final ImageIcon KEY_H = new ImageIcon(ConnectionTreeCellRender.class.getResource("/org/openpackage/redismanager/ui/assets/x16/H.png"));
    private static final ImageIcon KEY_L = new ImageIcon(ConnectionTreeCellRender.class.getResource("/org/openpackage/redismanager/ui/assets/x16/L.png"));
    private static final ImageIcon KEY_Z = new ImageIcon(ConnectionTreeCellRender.class.getResource("/org/openpackage/redismanager/ui/assets/x16/Z.png"));

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        if (!node.isRoot()) {
            if (node.getUserObject() instanceof RedisKey) {
                RedisKey rkey = (RedisKey)node.getUserObject();
                this.setTextNonSelectionColor(Color.BLUE);
                switch(rkey.getType()){
                    case "string":
                        this.setIcon(KEY_T); break;
                    case "set":
                        this.setIcon(KEY_S); break;
                    case "hash":
                        this.setIcon(KEY_H); break;
                    case "list":
                        this.setIcon(KEY_L); break;
                    case "zset":
                        this.setIcon(KEY_Z); break;
                }
                
            }
        }
        return this;
    }

}
