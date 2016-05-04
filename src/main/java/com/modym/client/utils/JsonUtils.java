/************************************************************************
 * Copyright MODYM, Ltd.
 */
package com.modym.client.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.joda.JodaModule;

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
    public static String encode(Object object) {
        try {
            return JsonUtils.OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param object
     * @return
     */
    public static String encodePretty(Object object) {
        try {
            JsonUtils.OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
            return JsonUtils.OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            JsonUtils.OBJECT_MAPPER.disable(SerializationFeature.INDENT_OUTPUT);
        }
        return "";
    }

    public static Map<String, Object> decode(InputStream inputStream) {
        try {
            TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() {};
            return JsonUtils.OBJECT_MAPPER.readValue(inputStream, typeReference);
        } catch (JsonParseException e) {
        } catch (JsonMappingException e) {
        } catch (IOException e) {
        }
        return Collections.emptyMap();
    }

    public static <T> T decode(InputStream inputStream, Class<T> castAs) {
        try {
            return JsonUtils.OBJECT_MAPPER.readValue(inputStream, castAs);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Object> decode(String jsonString) {
        try {
            TypeReference<HashMap<String, Object>> typeReference = new TypeReference<HashMap<String, Object>>() {};
            return JsonUtils.OBJECT_MAPPER.readValue(jsonString, typeReference);
        } catch (JsonParseException e) {
        } catch (JsonMappingException e) {
        } catch (IOException e) {
        }
        return Collections.emptyMap();
    }

    public static <T> T decode(String jsonString, Class<T> castAs) {
        try {
            return JsonUtils.OBJECT_MAPPER.readValue(jsonString, castAs);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
