package com.github.yeriomin.andtest.core;

import org.json.JSONObject;
import org.junit.*;

public class AnswerOpenEndedTest {

    static private final String JSON = "{\"a\":\"ans\"}";

    @org.junit.Test
    public void toJSONObject() throws Exception {
        AnswerOpenEnded answer = new AnswerOpenEnded("ans");
        Assert.assertEquals(JSON, answer.toJSONString());
        JSONObject object = answer.toJSONObject();
        Assert.assertTrue(object.has("a"));
        String answerValue = (String) object.get("a");
        Assert.assertEquals("ans", answerValue);
    }

}