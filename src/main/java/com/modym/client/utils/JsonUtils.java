/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.utils;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.modym.client.ModymClientException;

/**
 * @author bashar
 */
public class JsonUtils {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private static final Map<Class<?>, Boolean> ENCODE_DECODE_MAP = new ConcurrentHashMap<>();

    static {
        ObjectMapper mapper = JsonUtils.OBJECT_MAPPER;
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS, true);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        mapper.registerModule(new JodaModule());
        SimpleFilterProvider filters = new SimpleFilterProvider();
        filters.setDefaultFilter(SimpleBeanPropertyFilter.serializeAllExcept(new String[] {}));
        filters.setFailOnUnknownId(false);
        mapper.setFilters(filters);
    }

    private JsonUtils() {
    }

    /**
     * @return the mapper
     */
    public static ObjectMapper getObjectMapper() {
        return JsonUtils.OBJECT_MAPPER;
    }

    /**
     * @param object
     * @return
     */
    public static boolean canEncodeDecode(Object object) {
        Class<?> objectClass = object.getClass();
        if (JsonUtils.ENCODE_DECODE_MAP.containsKey(objectClass))
            return JsonUtils.ENCODE_DECODE_MAP.get(objectClass);
        JavaType objectType = TypeFactory.defaultInstance().constructType(objectClass);
        boolean canEncodeDecode =
                JsonUtils.OBJECT_MAPPER.canSerialize(objectClass) && JsonUtils.OBJECT_MAPPER.canDeserialize(objectType);
        JsonUtils.ENCODE_DECODE_MAP.put(objectClass, canEncodeDecode);
        return canEncodeDecode;
    }

    /**
     * @param object
     * @return
     */
    public static String encode(Object object) throws ModymClientException {
        try {
            return JsonUtils.OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new ModymClientException("Failed to encode object", e);
        }
    }

    /**
     * @param object
     * @return
     */
    public static String encodePretty(Object object) throws ModymClientException {
        try {
            JsonUtils.OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
            return JsonUtils.OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            throw new ModymClientException("Failed to encode object", e);
        } finally {
            JsonUtils.OBJECT_MAPPER.disable(SerializationFeature.INDENT_OUTPUT);
        }
    }

    public static Map<String, Object> decode(InputStream inputStream) throws ModymClientException {
        try {
            TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() {};
            return JsonUtils.OBJECT_MAPPER.readValue(inputStream, typeReference);
        } catch (Exception e) {
            throw new ModymClientException("Failed to decode inputstring", e);
        }
    }

    public static <T> T decode(InputStream inputStream, Class<T> castAs) throws ModymClientException {
        try {
            return JsonUtils.OBJECT_MAPPER.readValue(inputStream, castAs);
        } catch (Exception e) {
            throw new ModymClientException("Failed to decode inputstream", e);
        }
    }

    public static Map<String, Object> decode(String jsonString) throws ModymClientException {
        try {
            TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() {};
            return JsonUtils.OBJECT_MAPPER.readValue(jsonString, typeReference);
        } catch (Exception e) {
            throw new ModymClientException("Failed to decode input string", e);
        }
    }

    public static <T> T decode(String jsonString, Class<T> castAs) throws ModymClientException {
        try {
            return JsonUtils.OBJECT_MAPPER.readValue(jsonString, castAs);
        } catch (Exception e) {
            throw new ModymClientException("Failed to decode input string", e);
        }
    }

}
