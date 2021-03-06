/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager;

import java.util.Collection;
import java.util.Observable;

/**
 *
 * @author micfans
 */
public class RedisKeys extends Observable {

    private final RedisDb rd;

    private long keySize = -1;

    @Override
    public String toString() {
        return "Keys" + " (" + getKeySize() + ")";
    }

    public RedisKeys(RedisDb rd) {
        this.rd = rd;
    }

    public RedisDb getRd() {
        return rd;
    }

    public Collection<String> getKeys() {
        return getKeys("*");
    }

    public Collection<String> getKeys(String filter) {
        return rd.getKeys(filter);
    }

    public boolean delete(String name) {
        boolean b = rd.delete(name);
        if (b) {
            rd.getKeySize();
        }
        return b;
    }

    public long getKeySize() {
        long c = rd.getKeySize();
        if (c != keySize) {
            keySize = c;
            super.setChanged();
            super.notifyObservers();
        }
        return keySize;
    }
}
