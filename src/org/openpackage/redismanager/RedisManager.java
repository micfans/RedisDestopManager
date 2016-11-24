/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author micfans
 */
public class RedisManager {

    private static final Map<String, RedisConnection> CONNECTIONS = new HashMap();

    private static Collection<RedisConfig> getRedisConfigs() {
        Collection<RedisConnection> coll = CONNECTIONS.values();
        Collection rss = new ArrayList();
        coll.forEach((rc) -> {
            rss.add(rc.config());
        });
        return rss;
    }

    private static void initFromRedisConfigs(Collection<RedisConfig> rss) {
        CONNECTIONS.clear();
        rss.forEach((rs) -> {
            RedisManager.add(rs);
        });
    }

    private static synchronized File getStoreFile() throws IOException {
        String dir = System.getProperty("user.dir");
        String file = dir + "/rm.dat";
        File f = new File(file);
        return f;
    }

    public static synchronized void load() throws IOException, ClassNotFoundException {
        File file = getStoreFile();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return;
        }
        try (InputStream in = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(in)) {
            Collection rss = (Collection) ois.readObject();
            initFromRedisConfigs(rss);
        }
    }

    public static synchronized void save() throws IOException {
        File file = getStoreFile();
        try (OutputStream os = new FileOutputStream(file); ObjectOutputStream ois = new ObjectOutputStream(os)) {
            Collection rss = getRedisConfigs();
            ois.writeObject(rss);
        }
    }

    public static boolean add(RedisConfig config) {
        RedisConnection rc = new RedisConnection(config);
        return add(rc);
    }

    public static boolean add(RedisConnection connection) {
        if (!CONNECTIONS.containsKey(connection.config().name)) {
            RedisConnection rc = new RedisConnection(connection.config());
            CONNECTIONS.put(connection.config().name, rc);
            try {
                save();
            } catch (IOException ex) {
                ex.printStackTrace(System.err);
                return false;
            }
            return true;
        }
        return false;
    }

    public static void remove(RedisConnection connection) {

    }

    public static RedisConnection get(String name) {
        return CONNECTIONS.get(name);
    }

    public static Collection<RedisConnection> getAll() throws IOException, ClassNotFoundException {
        if (CONNECTIONS.isEmpty()) {
            load();
        }
        return CONNECTIONS.values();
    }
}
