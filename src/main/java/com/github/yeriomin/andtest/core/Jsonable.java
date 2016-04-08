package com.github.yeriomin.andtest.core;

import org.json.JSONObject;

public interface Jsonable {
    JSONObject toJSONObject();
    String toJSONString();
}
