package com.github.yeriomin.andtest.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class QuestionMultipleChoiceTest extends TestCase {

    private final HashMap<String, Object> question = new HashMap<>();

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

    public void testQuestionMultipleChoiceFromJson() throws JSONException
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

    public void testQuestionMultipleChoiceToJson()
    {
        try {
            com.github.yeriomin.andtest.core.QuestionMultipleChoice question = new com.github.yeriomin.andtest.core.QuestionMultipleChoice(this.question);
            ArrayList<String> choices = new ArrayList<>();
            choices.add("Jack");
            choices.add("Jill");
            question.setChoices(choices);
            HashSet<Integer> correct = new HashSet<>();
            correct.add(0);
            question.setCorrect(correct);

            JSONObject object = question.toJSONObject();

            assertEquals("multipleChoice", object.getString("type"));
            assertTrue(object.has("choices"));
            assertEquals(2, object.getJSONArray("choices").length());
            assertEquals("Jack", object.getJSONArray("choices").getString(0));
            assertEquals("Jill", object.getJSONArray("choices").getString(1));
            assertTrue(object.has("correct"));
            assertEquals(1, object.getJSONArray("correct").length());
            assertEquals(0, object.getJSONArray("correct").getInt(0));
        } catch (JSONException e) {
            // no need
        }
    }

    public void testQuestionMultipleChoiceCorrectness()
    {
        try {
            com.github.yeriomin.andtest.core.QuestionMultipleChoice question = new com.github.yeriomin.andtest.core.QuestionMultipleChoice(this.question);
            ArrayList<String> choices = new ArrayList<>();
            choices.add("Jack");
            choices.add("Jill");
            question.setChoices(choices);
            HashSet<Integer> correct = new HashSet<>();
            correct.add(0);
            question.setCorrect(correct);

            assertFalse(question.isCorrect(new AnswerMultipleChoice()));
            assertTrue(question.isCorrect(new AnswerMultipleChoice(correct)));
            HashSet<Integer> incorrect = new HashSet<>();
            incorrect.add(1);
            assertFalse(question.isCorrect(new AnswerMultipleChoice(incorrect)));
        } catch (JSONException e) {
            // no need
        }
    }
}
