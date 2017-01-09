package com.github.yeriomin.andtest.core;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class TestStateTest {

    static private final String JSON = "{\"description\":\"test test\",\"timeLimit\":600,\"questions\":[{\"question\":\"What is my name?\",\"type\":\"multipleChoice\",\"choices\":[\"Jack\",\"Jill\"],\"correct\":[0],\"explanation\":\"My mum named me\"}]}";

    @org.junit.Test
    public void start() throws Exception {
        TestState state = new TestState();
        Assert.assertFalse(state.isStarted());
        Assert.assertEquals(0, state.getStartedAt());
        state.start();
        Assert.assertTrue(state.isStarted());
        state.setStartedAt(0);
        Assert.assertFalse(state.isStarted());
        state.setStartedAt(10);
        Assert.assertTrue(state.isStarted());
        Assert.assertEquals(10, state.getStartedAt());
    }

    @Test
    public void finish() throws Exception {
        TestState state = new TestState();
        Assert.assertFalse(state.isFinished());
        Assert.assertEquals(0, state.getFinishedAt());
        state.finish();
        Assert.assertTrue(state.isFinished());
        state.setFinishedAt(0);
        Assert.assertFalse(state.isFinished());
        state.setFinishedAt(10);
        Assert.assertTrue(state.isFinished());
        Assert.assertEquals(10, state.getFinishedAt());
    }

    @Test
    public void getCorrectAnswerCount() throws Exception {
        TestState state = new TestState();
        Assert.assertEquals(0, state.getCorrectAnswerCount(new com.github.yeriomin.andtest.core.Test()));
        state.setAnswer(1, new AnswerOpenEnded("Extra"));
        Set<Integer> answerMc = new HashSet<>();
        answerMc.add(1);
        state.setAnswer(0, new AnswerMultipleChoice(answerMc));
        com.github.yeriomin.andtest.core.Test test = new com.github.yeriomin.andtest.core.Test(new JSONObject(JSON));
        answerMc.add(0);
        state.setAnswer(0, new AnswerMultipleChoice(answerMc));
        Assert.assertEquals(0, state.getCorrectAnswerCount(test));
        answerMc.remove(1);
        state.setAnswer(0, new AnswerMultipleChoice(answerMc));
        Assert.assertEquals(1, state.getCorrectAnswerCount(test));
    }

    @Test
    public void isAnswered() {
        TestState state = new TestState();
        Assert.assertFalse(state.isAnswered(5));
        state.setAnswer(5, new AnswerOpenEnded());
        Assert.assertFalse(state.isAnswered(5));
        Assert.assertTrue(state.getAnswers().containsKey(5));
        state.setAnswer(5, new AnswerOpenEnded("aaa"));
        Assert.assertTrue(state.isAnswered(5));
        Assert.assertEquals("aaa", ((AnswerOpenEnded) state.getAnswer(5)).get());
    }

    @Test
    public void getAnswer() {
        TestState state = new TestState();
        Assert.assertFalse(state.isAnswered(5));
        Assert.assertNull(state.getAnswer(5));
        state.setAnswer(5, new AnswerOpenEnded());
        Assert.assertFalse(state.isAnswered(5));
        Assert.assertFalse(null == state.getAnswer(5));
        state.setAnswer(5, new AnswerOpenEnded("bbb"));
        Assert.assertTrue(state.isAnswered(5));
        Assert.assertEquals("bbb", ((AnswerOpenEnded) state.getAnswer(5)).get());
    }

}