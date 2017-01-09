package com.github.yeriomin.andtest.core;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class TestState {

    private long startedAt;
    private long finishedAt;
    private Map<Integer, Answer> answers = new HashMap<>();

    public long getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(long startedAt) {
        this.startedAt = startedAt;
    }

    public boolean isStarted() {
        return this.startedAt > 0;
    }

    public void start() {
        this.startedAt = Calendar.getInstance().getTimeInMillis();
    }

    public long getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(long finishedAt) {
        this.finishedAt = finishedAt;
    }

    public boolean isFinished() {
        return this.finishedAt > 0;
    }

    public void finish() {
        this.finishedAt = Calendar.getInstance().getTimeInMillis();
    }

    public Map<Integer, Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Map<Integer, Answer> answers) {
        this.answers = answers;
    }

    public Answer getAnswer(int questionNum) {
        return isAnswered(questionNum) ? this.answers.get(questionNum) : null;
    }

    public void setAnswer(int questionNum, Answer answer) {
        this.answers.put(questionNum, answer);
    }

    public boolean isAnswered(int questionNum) {
        return this.answers.containsKey(questionNum) && !this.answers.get(questionNum).isEmpty();
    }

    public int getCorrectAnswerCount(Test test) {
        int count = 0;
        for (Integer questionNum: getAnswers().keySet()) {
            if (questionNum < test.getQuestions().size()
                    && test.getQuestions().get(questionNum).isCorrect(getAnswer(questionNum))) {
                count++;
            }
        }
        return count;
    }
}
