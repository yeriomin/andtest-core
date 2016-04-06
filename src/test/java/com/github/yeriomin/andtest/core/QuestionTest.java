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

    public void testQuestionMultipleChoice()
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
}
