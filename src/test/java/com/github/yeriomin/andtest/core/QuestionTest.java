package com.github.yeriomin.andtest.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONObject;

public class QuestionTest extends TestCase {

    static private final JSONObject question;
    static {
        question = new JSONObject();
        question.put("question", "What is my name?");
        question.put("type", "openEnded");
        question.put("correct", "Jack");
        question.put("explanation", "My mum named me like that.");
    }

    public QuestionTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(QuestionTest.class);
    }

    public void testQuestionMultipleChoice()
    {
        com.github.yeriomin.andtest.core.Question question = com.github.yeriomin.andtest.core.Question.of(this.question);
        assertEquals("What is my name?", question.getQuestion());
        assertEquals("My mum named me like that.", question.getExplanation());
        assertEquals("openEnded", question.getType());
    }
}
