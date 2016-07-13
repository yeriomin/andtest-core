package com.github.yeriomin.andtest.core;

import junit.framework.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.*;

public class JsonConverterTest extends TestCase {


    public static junit.framework.Test suite()
    {
        return new TestSuite(JsonConverterTest.class);
    }

    public void testConvertArray() {
        List list = Arrays.asList(3, 4, 5);
        Map map = new HashMap();
        map.put("key1", "value1");
        JSONArray array = new JSONArray(Arrays.asList(1, "two", list, new JSONArray(list), map, new JSONObject(map)));
        try {
            List result = com.github.yeriomin.andtest.core.JSONConverter.toList(array);
            assertEquals(6, result.size());
            assertEquals(1, result.get(0));
            assertEquals("two", result.get(1));
            assertEquals(true, result.get(2) instanceof List);
            assertEquals(3, ((List) result.get(2)).size());
            assertEquals(5, ((List) result.get(2)).get(2));
            assertEquals(true, result.get(3) instanceof List);
            assertEquals(3, ((List) result.get(3)).size());
            assertEquals(5, ((List) result.get(3)).get(2));
            assertEquals(true, result.get(4) instanceof Map);
            assertEquals(1, ((Map) result.get(4)).size());
            assertEquals("value1", ((Map) result.get(4)).get("key1"));
            assertEquals(true, result.get(5) instanceof Map);
            assertEquals(1, ((Map) result.get(5)).size());
            assertEquals("value1", ((Map) result.get(5)).get("key1"));
        } catch (JSONException e) {
            // no need
        }
    }

    public void testConvertObject()
    {
        List list = Arrays.asList(1, 2, 3);
        Map map = new HashMap();
        map.put("key1", "value1");
        JSONObject object = new JSONObject();
        object.put("key1", 1);
        object.put("key2", "two");
        object.put("key3", list);
        object.put("key4", new JSONArray(list));
        object.put("key5", map);
        object.put("key6", new JSONObject(map));
        try {
            Map result = com.github.yeriomin.andtest.core.JSONConverter.toMap(object);
            assertEquals(6, result.size());
            assertEquals(1, result.get("key1"));
            assertEquals("two", result.get("key2"));
            assertEquals(true, result.get("key3") instanceof List);
            assertEquals(3, ((List) result.get("key3")).size());
            assertEquals(3, ((List) result.get("key3")).get(2));
            assertEquals(true, result.get("key4") instanceof List);
            assertEquals(3, ((List) result.get("key4")).size());
            assertEquals(3, ((List) result.get("key4")).get(2));
            assertEquals(true, result.get("key5") instanceof Map);
            assertEquals(1, ((Map) result.get("key5")).size());
            assertEquals("value1", ((Map) result.get("key5")).get("key1"));
            assertEquals(true, result.get("key6") instanceof Map);
            assertEquals(1, ((Map) result.get("key6")).size());
            assertEquals("value1", ((Map) result.get("key6")).get("key1"));
        } catch (JSONException e) {
            // no need
        }
    }
}
