/************************************************************************ 
 * Copyright MODYM, Ltd.
 */
package com.modym.client.objects;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;

/**
 * @author bashar
 *
 */
public class UDFType {

    private Map<String, Object> objectMap = new HashMap<>();

    public Object get(String key) {
        return this.objectMap.get(key);
    }

    public void set(String key, Object value) {
        this.objectMap.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getUserDefinedFields() {
        return this.objectMap;
    }

}
