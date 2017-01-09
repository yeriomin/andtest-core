package com.github.yeriomin.andtest.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class AnswerMultipleChoice extends Answer {

    protected Set<Integer> answer = new HashSet<>();

    {
        type = Question.TYPE_MC;
    }

    public AnswerMultipleChoice() {
    }

    public AnswerMultipleChoice(Set<Integer> answer) {
        set(answer);
    }

    public Set<Integer> get() {
        return answer;
    }

    public void set(Set<Integer> answer) {
        this.answer = answer;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put(JSON_PROPERTY_VALUE, new JSONArray(get()));
        return object;
    }

    @Override
    public void fill(String jsonString) throws JSONException {
        JSONObject object = new JSONObject(jsonString);
        if (!object.has(JSON_PROPERTY_VALUE)) {
            throw new JSONException("No answer container in JSON");
        }
        Set<Integer> set = new HashSet<>();
        for (Object answer: object.getJSONArray(JSON_PROPERTY_VALUE)) {
            set.add((Integer) answer);
        }
        set(set);
    }

    @Override
    public boolean isEmpty() {
        return null == this.answer || this.answer.isEmpty();
    }
}
