package com.github.yeriomin.andtest.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuestionMultipleChoiceTest extends TestCase {

    private final JSONObject question = new JSONObject();

    public QuestionMultipleChoiceTest(String testName)
    {
        super(testName);

        try {
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
        } catch (JSONException e) {
            // no need
        }
    }

    public static Test suite()
    {
        return new TestSuite(QuestionMultipleChoiceTest.class);
    }

    public void testQuestionMultipleChoice() throws JSONException
    {
        try {
            com.github.yeriomin.andtest.core.QuestionMultipleChoice question = new com.github.yeriomin.andtest.core.QuestionMultipleChoice(this.question);
            assertEquals("multipleChoice", question.getType());
            assertEquals(2, question.getChoices().size());
            assertEquals(true, question.getChoices().contains("Jack"));
            assertEquals(true, question.getChoices().contains("Jill"));
            assertEquals(1, question.getCorrect().size());
            assertEquals(true, question.getCorrect().contains(0));
        } catch (JSONException e) {
            // no need
        }
    }
}
