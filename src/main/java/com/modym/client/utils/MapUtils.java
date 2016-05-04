/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.utils;

/**
 * @author bashar
 */
import java.util.HashMap;
import java.util.Map;

/**
 * @author $Author: bsaleh $
 * @version $Revision: 1.1 $
 */
public class MapUtils extends org.apache.commons.collections.MapUtils {
    public static Map<String, Object> asMap(Object... objects) {
        if (objects.length % 2 != 0) {
            return null;
        }
        for (int i = 0; i < objects.length; i = i + 2) {
            if (!(objects[i] instanceof String)) {
                return null;
            }
        }
        Map<String, Object> map = new HashMap<String, Object>();
        for (int i = 0; i < objects.length; i = i + 2) {
            map.put((String) objects[i], objects[i + 1]);
        }
        return map;
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, T> asMap(Class<T> clazz, Object... objects) {
        if (objects.length % 2 != 0 || clazz == null) {
            return null;
        }
        for (int i = 0; i < objects.length; i = i + 2) {
            if (!(objects[i] instanceof String) && !(clazz.isInstance(objects[i + 1]))) {
                return null;
            }
        }
        Map<String, T> map = new HashMap<String, T>();
        for (int i = 0; i < objects.length; i = i + 2) {
            map.put((String) objects[i], (T) objects[i + 1]);
        }
        return map;
    }
}