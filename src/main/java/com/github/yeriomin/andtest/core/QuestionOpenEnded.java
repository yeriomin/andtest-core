package com.github.yeriomin.andtest.core;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

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

    public QuestionOpenEnded(Map map) throws JSONException {
        super(map);

        this.setAnswer(new String());
        this.type = Question.TYPE_OE;
        if (!map.containsKey(JSON_PROPERTY_CORRECT)) {
            throw new JSONException("correct field missing");
        }
        this.correct = (String) map.get(JSON_PROPERTY_CORRECT);
    }

    public JSONObject toJSONObject () {
        JSONObject object = super.toJSONObject();
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
