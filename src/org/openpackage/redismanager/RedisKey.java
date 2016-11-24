/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openpackage.redismanager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author micfans
 */
public class RedisKey {

    public static final String VALUE_STRING = "Plain Text";
    public static final String VALUE_JSON = "Json Text";

    private final RedisKeys rks;
    private String key;
    private Object value;
    private long valueSize;

    @Override
    public String toString() {
        return key + (valueSize > -1 ? " (" + valueSize + ")" : "");
    }

    public RedisKey(String key, RedisKeys rks) {
        this.valueSize = -1;
        this.key = key;
        this.rks = rks;
    }

    public void renameKey(String newKey) {
        this.key = newKey;
    }

    public long getValueSize() throws IOException {
        if (value != null) {
            ByteArrayOutputStream buf = new ByteArrayOutputStream(4096);
            ObjectOutputStream out = new ObjectOutputStream(buf);
            out.writeObject(value);
            out.flush();
            buf.close();
            valueSize = buf.size();
        }
        return valueSize;
    }

    public void loadValue() {
        value = rks.getRd().getKeyValue(key);
    }

    public String getName() {
        return key;
    }

    public String getValue() {
        return getValue(VALUE_STRING);
    }

    public String getValue(String valueType) {
        switch (valueType) {
            case VALUE_STRING:
                return value == null ? "" : value.toString();
        }
        throw new IllegalArgumentException("Unknown value type for " + valueType);
    }

}
