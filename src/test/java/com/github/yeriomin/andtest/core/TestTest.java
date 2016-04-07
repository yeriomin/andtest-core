package com.github.yeriomin.andtest.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TestTest extends TestCase {

    static private final String JSON_MINIMAL = "{\"questions\":[]}";
    static private final String JSON_WITH_PROPERTIES = "{\"description\":\"test test\",\"timeLimit\":600,\"questions\":[{\"question\":\"What is my name?\",\"type\":\"multipleChoice\",\"choices\":[\"Jack\",\"Jill\"],\"correct\":[0],\"explanation\":\"My mum named me\"}]}";

    public TestTest(String testName)
    {
        super(testName);
    }

    public static Test suite()
    {
        return new TestSuite(TestTest.class);
    }

    public void testTestMinimal() throws JSONException
    {
        com.github.yeriomin.andtest.core.Test test = new com.github.yeriomin.andtest.core.Test(new JSONObject(JSON_MINIMAL));
        assertEquals(0, test.getQuestions().size());
        assertEquals(0, test.getCorrectCount());
        assertEquals(0, test.getTimeLimit());
        assertEquals("", test.getDescription());
    }

    public void testTestWithProperties() throws JSONException
    {
        com.github.yeriomin.andtest.core.Test test = new com.github.yeriomin.andtest.core.Test(new JSONObject(JSON_WITH_PROPERTIES));
        assertEquals(1, test.getQuestions().size());
        assertEquals(0, test.getCorrectCount());
        assertEquals(600, test.getTimeLimit());
        assertEquals("test test", test.getDescription());
    }

    public void testTestToJson() throws JSONException
    {
        com.github.yeriomin.andtest.core.Test test = new com.github.yeriomin.andtest.core.Test();
        test.setDescription("test test");
        test.setTimeLimit(600);
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new QuestionMultipleChoice());
        test.setQuestions(questions);

        JSONObject object = test.toJsonObject();
        assertTrue(object.has("questions"));
        assertEquals(1, object.getJSONArray("questions").length());
        assertEquals(600, object.getInt("timeLimit"));
        assertEquals("test test", object.getString("description"));
    }
}
