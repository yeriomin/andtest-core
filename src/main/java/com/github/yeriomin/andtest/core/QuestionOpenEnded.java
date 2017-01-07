package com.github.yeriomin.andtest.core;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class QuestionOpenEnded extends Question {

    private static final String JSON_PROPERTY_CORRECT = "correct";

    private String correct;

    {
        type = Question.TYPE_OE;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public QuestionOpenEnded(Map map) throws JSONException {
        super(map);

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

    public boolean isCorrect(Answer answer) {
        return answer instanceof AnswerOpenEnded && this.correct.equals(((AnswerOpenEnded) answer).get());
    }

}
