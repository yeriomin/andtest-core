package com.github.yeriomin.andtest.core;

abstract public class Answer implements Jsonable {

    protected final static String JSON_PROPERTY_VALUE = "a";

    public String toJSONString() {
        return this.toJSONObject().toString();
    }
}
