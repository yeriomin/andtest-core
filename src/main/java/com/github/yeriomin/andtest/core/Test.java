package com.github.yeriomin.andtest.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    public Test(Map map) throws JSONException {
        this();

        fill(map);
    }

    public Test(JSONObject object) throws JSONException {
        this();

        HashMap<String, Object> map = new HashMap<>();
        for (String key: object.keySet()) {
            map.put(key, object.get(key));
        }
        fill(map);
    }

    protected void fill(Map map) throws JSONException {
        this.questions = new ArrayList<>();
        if (!map.containsKey(JSON_PROPERTY_QUESTIONS)) {
            throw new JSONException("questions field missing");
        }
        Iterable questions = (Iterable) map.get(JSON_PROPERTY_QUESTIONS);
        while (questions.iterator().hasNext()) {
            this.questions.add(Question.of(new JSONObject(questions.iterator().next())));
        }
        if (map.containsKey(JSON_PROPERTY_TIMELIMIT)) {
            this.timeLimit = (Integer) map.get(JSON_PROPERTY_TIMELIMIT);
        }
        if (map.containsKey(JSON_PROPERTY_DESCRIPTION)) {
            this.description = (String) map.get(JSON_PROPERTY_DESCRIPTION);
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
