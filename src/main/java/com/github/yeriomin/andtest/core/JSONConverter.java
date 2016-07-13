package com.github.yeriomin.andtest.core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * Why isn't JSONObject a map?
 */
public class JSONConverter {

    public static Map toMap(JSONObject object) {
        HashMap<String, Object> map = new HashMap<>();
        Iterator<String> keys = object.keys();
        while (keys.hasNext()) {
            String key = keys.next();
            Object subObject = object.get(key);
            if (subObject instanceof JSONObject) {
                map.put(key, toMap((JSONObject) subObject));
            } else if (subObject instanceof JSONArray) {
                map.put(key, toList((JSONArray) subObject));
            } else {
                map.put(key, subObject);
            }
        }
        return map;
    }

    public static List toList(JSONArray array) {
        ArrayList arrayList = new ArrayList();
        int size = array.length();
        for (int i = 0; i < size; i++) {
            Object object = array.get(i);
            if (object instanceof JSONObject) {
                arrayList.add(toMap((JSONObject) object));
            } else if (object instanceof JSONArray) {
                arrayList.add(toList((JSONArray) object));
            } else {
                arrayList.add(object);
            }
        }
        return arrayList;
    }
}
