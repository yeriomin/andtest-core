package com.github.yeriomin.andtest.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.util.HashSet;
import java.util.Set;

public class AnswerMultipleChoiceTest {

    static private final String JSON = "{\"a\":[2,4]}";

    @Rule
    public final ExpectedException exception = ExpectedException.none();

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

    @org.junit.Test
    public void fill() {
        AnswerMultipleChoice answer = new AnswerMultipleChoice();
        answer.fill(JSON);
        Assert.assertEquals(2, answer.get().size());
        Assert.assertTrue(answer.get().contains(2));
        Assert.assertTrue(answer.get().contains(4));
        Assert.assertFalse(answer.get().contains(3));
        exception.expect(JSONException.class);
        answer.fill("{}");
    }

}