package com.github.yeriomin.andtest.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONException;
import org.json.JSONObject;

public class QuestionTest extends TestCase {

    private final JSONObject question = new JSONObject();

    public QuestionTest(String testName)
    {
        super(testName);

        try {
            question.put("question", "What is my name?");
            question.put("type", "openEnded");
            question.put("correct", "Jack");
            question.put("explanation", "My mum named me like that.");
        } catch (JSONException e) {
            // no need
        }
    }

    public static Test suite()
    {
        return new TestSuite(QuestionTest.class);
    }

    public void testQuestionFromJson()
    {
        try {
            com.github.yeriomin.andtest.core.Question question = new com.github.yeriomin.andtest.core.QuestionOpenEnded(this.question);
            assertEquals("What is my name?", question.getQuestion());
            assertEquals("My mum named me like that.", question.getExplanation());
            assertEquals("openEnded", question.getType());
        } catch (JSONException e) {
            // no need
        }
    }

    public void testQuestionToJson()
    {
        try {
            com.github.yeriomin.andtest.core.QuestionOpenEnded question = new com.github.yeriomin.andtest.core.QuestionOpenEnded();
            question.setQuestion("What is my name?");
            question.setExplanation("My mum named me like that.");

            JSONObject object = question.toJSONObject();

            assertEquals("openEnded", object.getString("type"));
            assertEquals("What is my name?", object.getString("question"));
            assertEquals("My mum named me like that.", object.getString("explanation"));
        } catch (JSONException e) {
            // no need
        }
    }
}
