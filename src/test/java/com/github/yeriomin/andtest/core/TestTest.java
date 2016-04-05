package com.github.yeriomin.andtest.core;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class TestTest extends TestCase {

    static private final String JSON_EMPTY = "{}";
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

    public void testTestMinimal()
    {
        com.github.yeriomin.andtest.core.Test test = com.github.yeriomin.andtest.core.Test.of(JSON_MINIMAL);
        assertEquals(0, test.getQuestions().size());
        assertEquals(0, test.getCorrectCount());
        assertEquals(0, test.getTimeLimit());
        assertEquals("", test.getDescription());
    }

    public void testTestWithProperties()
    {
        com.github.yeriomin.andtest.core.Test test = com.github.yeriomin.andtest.core.Test.of(JSON_WITH_PROPERTIES);
        assertEquals(1, test.getQuestions().size());
        assertEquals(0, test.getCorrectCount());
        assertEquals(600l, test.getTimeLimit());
        assertEquals("test test", test.getDescription());
    }
}
