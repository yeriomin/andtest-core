package com.github.yeriomin.andtest.core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Why isn't JSONObject a map?
 */
public class JSONConverter {

    public static HashMap toMap(JSONObject object) {
        HashMap<String, Object> map = new HashMap<>();
        for (String key: object.keySet()) {
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

    public static ArrayList toList(JSONArray array) {
        ArrayList arrayList = new ArrayList();
        for (Object object: array) {
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
