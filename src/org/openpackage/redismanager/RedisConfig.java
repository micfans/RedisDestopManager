/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager;

import java.io.Serializable;
import redis.clients.jedis.Protocol;

/**
 *
 * @author micfans
 */
public class RedisConfig implements Serializable{
    
    public String name;
    public String host = "localhost";
    public int port = 6379;
    public String password = null;
    public int timeout = Protocol.DEFAULT_TIMEOUT;
}
