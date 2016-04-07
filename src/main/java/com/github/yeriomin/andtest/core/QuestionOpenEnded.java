package com.github.yeriomin.andtest.core;

import org.json.JSONException;
import org.json.JSONObject;

public class QuestionOpenEnded extends Question {

    private static final String JSON_PROPERTY_CORRECT = "correct";

    private String correct;
    private String answer;

    public String getCorrect() {
        return correct;
    }

    public String getAnswer() {
        return answer;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionOpenEnded() {
        super();

        this.setAnswer(new String());
        this.type = Question.TYPE_OE;
    }

    public QuestionOpenEnded(JSONObject jsonQuestion) throws JSONException {
        super(jsonQuestion);

        this.setAnswer(new String());
        this.type = Question.TYPE_OE;
        this.correct = jsonQuestion.getString(JSON_PROPERTY_CORRECT);
    }

    public JSONObject toJsonObject () {
        JSONObject object = super.toJsonObject();
        object.put(JSON_PROPERTY_CORRECT, this.correct);
        return object;
    }

    public boolean isCorrect() {
        return this.correct.equals(this.getAnswer().toString().trim());
    }

    public boolean hasAnswer() {
        return null != this.getAnswer() && this.getAnswer().toString().length() > 0;
    }
}
