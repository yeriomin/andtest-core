package com.github.yeriomin.andtest.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONObject;

public class QuestionOpenEndedTest extends TestCase {

    static private final JSONObject question;
    static {
        question = new JSONObject();
        question.put("question", "What is my name?");
        question.put("type", "openEnded");
        question.put("correct", "Jack");
        question.put("explanation", "My mum named me like that.");
    }

    public QuestionOpenEndedTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(QuestionOpenEndedTest.class);
    }

    public void testQuestionMultipleChoice()
    {
        com.github.yeriomin.andtest.core.QuestionOpenEnded question = (com.github.yeriomin.andtest.core.QuestionOpenEnded) com.github.yeriomin.andtest.core.Question.of(this.question);
        assertEquals("openEnded", question.getType());
        assertEquals("Jack", question.getCorrect());
    }
}
