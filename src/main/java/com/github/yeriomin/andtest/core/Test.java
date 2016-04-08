package com.github.yeriomin.andtest.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Test implements Jsonable {
    
    private static final String JSON_PROPERTY_QUESTIONS = "questions";
    private static final String JSON_PROPERTY_TIMELIMIT = "timeLimit";
    private static final String JSON_PROPERTY_DESCRIPTION = "description";

    private String description = "";
    private long timeLimit;
    private ArrayList<Question> questions;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimeLimit(long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

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

    public Test() {
        super();

        this.questions = new ArrayList<>();
    }

    public Test(JSONObject object) throws JSONException {
        this();

        fill(object);
    }

    protected void fill(JSONObject object) throws JSONException {
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

    public String toJSONString() {
        return this.toJSONObject().toString(4);
    }

    public JSONObject toJSONObject () {
        JSONObject object = new JSONObject();
        if (null != this.description && this.description.length() > 0) {
            object.put(JSON_PROPERTY_DESCRIPTION, this.description);
        }
        if (this.timeLimit > 0) {
            object.put(JSON_PROPERTY_TIMELIMIT, this.timeLimit);
        }
        JSONArray questions = new JSONArray();
        for (Question question: this.getQuestions()) {
            questions.put(question.toJSONObject());
        }
        object.put(JSON_PROPERTY_QUESTIONS, questions);
        return object;
    }
}
