package com.github.yeriomin.andtest.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class QuestionOpenEndedTest extends TestCase {

    private final HashMap<String, Object> question = new HashMap<>();

    public QuestionOpenEndedTest(String testName)
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
        return new TestSuite(QuestionOpenEndedTest.class);
    }

    public void testQuestionOpenEndedFromJson()
    {
        try {
            com.github.yeriomin.andtest.core.QuestionOpenEnded question = new com.github.yeriomin.andtest.core.QuestionOpenEnded(this.question);
            assertEquals("openEnded", question.getType());
            assertEquals("Jack", question.getCorrect());
        } catch (JSONException e) {
            // no need
        }
    }

    public void testQuestionOpenEndedToJson()
    {
        try {
            com.github.yeriomin.andtest.core.QuestionOpenEnded question = new com.github.yeriomin.andtest.core.QuestionOpenEnded();
            question.setCorrect("Jack");

            JSONObject object = question.toJSONObject();

            assertEquals("openEnded", object.getString("type"));
            assertEquals("Jack", object.getString("correct"));
        } catch (JSONException e) {
            // no need
        }
    }

    public void testQuestionOpenEndedCorrectness()
    {
        try {
            com.github.yeriomin.andtest.core.QuestionOpenEnded question = new com.github.yeriomin.andtest.core.QuestionOpenEnded();
            question.setCorrect("Jack");

            assertFalse(question.hasAnswer());
            assertFalse(question.isCorrect());
            question.setAnswer("Jack");
            assertTrue(question.hasAnswer());
            assertTrue(question.isCorrect());
            question.setAnswer("Jill");
            assertTrue(question.hasAnswer());
            assertFalse(question.isCorrect());
        } catch (JSONException e) {
            // no need
        }
    }
}
