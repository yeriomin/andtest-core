package com.github.yeriomin.andtest.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONArray;
import org.json.JSONObject;

public class QuestionMultipleChoiceTest extends TestCase {

    static private final JSONObject question;
    static {
        question = new JSONObject();
        question.put("question", "What is my name?");
        question.put("type", "multipleChoice");
        JSONArray choices = new JSONArray();
        choices.put("Jack");
        choices.put("Jill");
        question.put("choices", choices);
        JSONArray correct = new JSONArray();
        correct.put(0);
        question.put("correct", correct);
        question.put("explanation", "My mum named me like that.");
    }

    public QuestionMultipleChoiceTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(QuestionMultipleChoiceTest.class);
    }

    public void testQuestionMultipleChoice()
    {
        com.github.yeriomin.andtest.core.QuestionMultipleChoice question = (com.github.yeriomin.andtest.core.QuestionMultipleChoice) com.github.yeriomin.andtest.core.Question.of(this.question);
        assertEquals("multipleChoice", question.getType());
        assertEquals(2, question.getChoices().size());
        assertEquals(true, question.getChoices().contains("Jack"));
        assertEquals(true, question.getChoices().contains("Jill"));
        assertEquals(1, question.getCorrect().size());
        assertEquals(true, question.getCorrect().contains(0));
    }
}
