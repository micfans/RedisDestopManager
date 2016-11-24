/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager.ui;

/**
 *
 * @author micfans
 */
public class DataChange {

    public static final String CONNECTION_CHANGED = "CONNECTION_CHANGED";
    public static final String LOG_ADDED = "LOG_ADDED";

    public String type;
    public Object arg;

    public DataChange(String type, Object arg) {
        this.type = type;
        this.arg = arg;
    }

    DataChange(String type) {
        this.type = type;
    }

}
