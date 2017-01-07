package com.github.yeriomin.andtest.core;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.*;

import java.util.HashSet;
import java.util.Set;

public class AnswerMultipleChoiceTest {

    static private final String JSON = "{\"a\":[2,4]}";

    @org.junit.Test
    public void toJSONObject() throws Exception {
        Set<Integer> set = new HashSet<>();
        set.add(2);
        set.add(4);
        AnswerMultipleChoice answer = new AnswerMultipleChoice(set);
        Assert.assertEquals(JSON, answer.toJSONString());
        JSONObject object = answer.toJSONObject();
        Assert.assertTrue(object.has("a"));
        JSONArray array = object.getJSONArray("a");
        Set<Integer> setResult = new HashSet<>();
        for (Object item: array) {
            setResult.add((Integer) item);
        }
        Assert.assertTrue(setResult.contains(2));
        Assert.assertTrue(setResult.contains(4));
    }

}