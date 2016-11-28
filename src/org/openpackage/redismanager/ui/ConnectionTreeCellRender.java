/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager.ui;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import org.openpackage.redismanager.RedisConnection;
import org.openpackage.redismanager.RedisDb;
import org.openpackage.redismanager.RedisKeys;

/**
 *
 * @author micfans
 */
public class ConnectionTreeCellRender extends DefaultTreeCellRenderer {

    private static final ImageIcon RC_ICON = new ImageIcon(ConnectionTreeCellRender.class.getResource("/org/openpackage/redismanager/ui/assets/x16/workstation.png"));
    private static final ImageIcon RD_ICON = new ImageIcon(ConnectionTreeCellRender.class.getResource("/org/openpackage/redismanager/ui/assets/x16/filing_cabinet.png"));
    private static final ImageIcon KEYS_ICON = new ImageIcon(ConnectionTreeCellRender.class.getResource("/org/openpackage/redismanager/ui/assets/x16/key.png"));

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        if (!node.isRoot()) {
            if (node.getUserObject() instanceof RedisConnection) {
                this.setIcon(RC_ICON);
            } else if (node.getUserObject() instanceof RedisDb) {
                this.setIcon(RD_ICON);
            }else if(node.getUserObject() instanceof RedisKeys){
                this.setIcon(KEYS_ICON);
            }
        }
        return this;
    }

}
