/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager.ui;

import java.io.IOException;
import java.util.Collection;
import java.util.Observable;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.openpackage.redismanager.RedisConfig;
import org.openpackage.redismanager.RedisConnection;
import org.openpackage.redismanager.RedisDb;
import org.openpackage.redismanager.RedisKeys;
import org.openpackage.redismanager.RedisManager;

/**
 *
 * @author micfans
 */
public class MainController extends Observable {

    public void log(String message, Throwable t) {
        System.out.println(message);
        t.printStackTrace(System.err);
        super.setChanged();
        super.notifyObservers(new DataChange(DataChange.LOG_ADDED, message));
    }

    public DefaultTreeModel loadConnections() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Connections");
        renderConnections(root);
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        return treeModel;
    }

    public void updateConnections(DefaultTreeModel model) {
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.removeAllChildren();
        renderConnections(root);
        model.reload();
    }

    public void renderConnections(DefaultMutableTreeNode root) {
        Collection<RedisConnection> list = null;
        try {
            list = RedisManager.getAll();
        } catch (IOException | ClassNotFoundException ex) {
            log(ex.getMessage(), ex);
        }
        if (list != null) {
            list.stream().map((rc) -> {
                DefaultMutableTreeNode sub = new DefaultMutableTreeNode(rc);
                return sub;
            }).forEachOrdered((sub) -> {
                root.add(sub);
            });
        }
    }

    public boolean saveConnection(RedisConfig config) {
        if (RedisManager.add(config)) {
            super.setChanged();
            super.notifyObservers(new DataChange(DataChange.CONNECTION_CHANGED));
            return true;
        }
        return false;
    }

    public void renderDbs(DefaultMutableTreeNode node) {
        RedisConnection rc = (RedisConnection) node.getUserObject();
        node.removeAllChildren();
        if (rc.connect()) {
            long dbs = rc.getDbs();
            int i = 0;
            while (i < dbs) {
                DefaultMutableTreeNode db = new DefaultMutableTreeNode();
                RedisDb rd = new RedisDb(rc, i);
                db.setUserObject(rd);
                node.add(db);
                i++;
            }
        }
    }

    public void renderKeys(DefaultMutableTreeNode node) {
        RedisDb rd = (RedisDb) node.getUserObject();
        node.removeAllChildren();
        if (rd.connect()) {
            RedisKeys rks = new RedisKeys(rd);
            DefaultMutableTreeNode keys = new DefaultMutableTreeNode(rks);
            node.add(keys);
        }
    }

}
