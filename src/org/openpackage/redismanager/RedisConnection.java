/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager;

import java.util.Set;
import redis.clients.jedis.Jedis;

/**
 *
 * @author micfans
 */
public class RedisConnection {

    private final Jedis jedis;
    private final RedisConfig config;

    public RedisConnection(RedisConfig config) {
        this.config = config;
        jedis = new Jedis(config.host, config.port, config.timeout);
    }

    @Override
    public String toString() {
        return config.name + (connected() ? " (" + getDbs() + ")" : "");
    }

    public RedisConfig config() {
        return config;
    }

    public boolean connect() {
        if (!connected()) {
            try {
                if (config.password != null) {
                    jedis.auth(config.password);
                } else {
                    jedis.connect();
                }
            } catch (Exception ex) {

            }
        }
        return connected();
    }

    public void disconnect() {
        jedis.disconnect();
    }

    public boolean connected() {
        return jedis.isConnected();
    }

    public void useDb(int index) {
        jedis.select(index);
    }

    public void set(String key, String value) {
        jedis.set(key, value);
    }

    public Object get(String key) {
        switch (getKeyType(key)) {
            case "string":
                return jedis.get(key);
            case "list":
                return jedis.lrange(key, 0, -1);
            case "set":
                return jedis.smembers(key);
            case "zset":
                return jedis.zrange(key, 0, -1);
            case "hash":
                return jedis.hgetAll(key);

        }
        return null;
    }

    public long getKeyTTL(String key) {
        return jedis.ttl(key);
    }

    public long getDbs() {
        return 10;
    }

    public long getKeySize() {
        return jedis.dbSize();
    }

    public Set<String> getKeys() {
        return getKeys("*");
    }

    public Set<String> getKeys(String filter) {
        return jedis.keys(filter);
    }

    boolean delete(String name) {
        return jedis.del(name) > 0;
    }

    String getKeyType(String key) {
        return jedis.type(key);
    }

}
