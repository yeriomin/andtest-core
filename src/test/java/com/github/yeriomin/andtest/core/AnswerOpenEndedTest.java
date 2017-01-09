package com.github.yeriomin.andtest.core;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.*;
import org.junit.rules.ExpectedException;

public class AnswerOpenEndedTest {

    static private final String JSON = "{\"a\":\"ans\"}";

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @org.junit.Test
    public void toJSONObject() throws Exception {
        AnswerOpenEnded answer = new AnswerOpenEnded("ans");
        Assert.assertEquals(JSON, answer.toJSONString());
        JSONObject object = answer.toJSONObject();
        Assert.assertTrue(object.has("a"));
        String answerValue = (String) object.get("a");
        Assert.assertEquals("ans", answerValue);
    }

    @org.junit.Test
    public void fill() {
        AnswerOpenEnded answer = new AnswerOpenEnded();
        answer.fill(JSON);
        Assert.assertEquals("ans", answer.get());
        exception.expect(JSONException.class);
        answer.fill("{}");
    }

}