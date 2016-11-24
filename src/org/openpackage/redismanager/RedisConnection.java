/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager;

import java.util.Collection;
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
        return jedis.get(key);
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

}
