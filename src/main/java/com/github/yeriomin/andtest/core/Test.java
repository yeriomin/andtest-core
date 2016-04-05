package com.github.yeriomin.andtest.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Test extends JSONObject {
    
    private static final String JSON_PROPERTY_QUESTIONS = "questions";
    private static final String JSON_PROPERTY_TIMELIMIT = "timeLimit";
    private static final String JSON_PROPERTY_DESCRIPTION = "description";

    private String description = "";
    private long timeLimit;
    private ArrayList<Question> questions;

    public long getTimeLimit() {
        return timeLimit;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public int getCorrectCount() {
        int count = 0;
        for (Question question: this.questions) {
            if (question.isCorrect()) {
                count++;
            }
        }
        return count;
    }

    private Test(JSONObject object) throws JSONException {
        super(object);

        this.questions = new ArrayList<>();
        JSONArray questions = object.getJSONArray(JSON_PROPERTY_QUESTIONS);
        for (int i = 0; i < questions.length(); i++) {
            this.questions.add(Question.of((JSONObject) questions.get(i)));
        }
        if (object.has(JSON_PROPERTY_TIMELIMIT)) {
            this.timeLimit = object.getInt(JSON_PROPERTY_TIMELIMIT);
        }
        if (object.has(JSON_PROPERTY_DESCRIPTION)) {
            this.description = object.getString(JSON_PROPERTY_DESCRIPTION);
        }
    }
    
    static public Test of(String content) throws JSONException {
        return new Test(new JSONObject(content));
    }
}
