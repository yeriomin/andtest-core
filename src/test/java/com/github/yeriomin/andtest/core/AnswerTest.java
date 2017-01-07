package com.github.yeriomin.andtest.core;

import org.junit.*;
import org.junit.rules.ExpectedException;

public class AnswerTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @org.junit.Test
    public void of() throws Exception {
        Answer answer1 = Answer.of(Question.TYPE_MC);
        Assert.assertTrue(answer1 instanceof AnswerMultipleChoice);
        Assert.assertEquals(Question.TYPE_MC, answer1.getType());
        Answer answer2 = Answer.of(Question.TYPE_OE);
        Assert.assertTrue(answer2 instanceof AnswerOpenEnded);
        Assert.assertEquals(Question.TYPE_OE, answer2.getType());
        exception.expect(Exception.class);
        Answer.of("fakeType");
    }

}