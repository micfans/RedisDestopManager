/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager;

import java.util.Collection;

/**
 *
 * @author micfans
 */
public class RedisDb {

    private final RedisConnection rc;

    private final int index;

    @Override
    public String toString() {
        return getTitle();
    }

    public RedisDb(RedisConnection rc, int index) {
        this.index = index;
        this.rc = rc;
    }
    
    public String getTitle(){
        return "db " + index;
    }
    
    public String getFullTitle(){
        return rc.config().name + ":" + getTitle();
    }

    public boolean connect() {
        boolean c = rc.connect();
        if (c) {
            rc.useDb(index);
        }
        return c;
    }

    public long getKeySize() {
        if (connect()) {
            return rc.getKeySize();
        }
        return -1;
    }

    public Collection<String> getKeys() {
        return getKeys("*");
    }

    Object getKeyValue(String key) {
        return rc.get(key);
    }

    Collection<String> getKeys(String filter) {
        return rc.getKeys(filter);
    }

}
