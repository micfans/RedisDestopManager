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
public class RedisKeys {

    private final RedisDb rd;

    @Override
    public String toString() {
        return "Keys" + " (" + rd.getKeySize() + ")";
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
    
    public Collection<String> getKeys(String filter){
        return rd.getKeys(filter);
    }
}
