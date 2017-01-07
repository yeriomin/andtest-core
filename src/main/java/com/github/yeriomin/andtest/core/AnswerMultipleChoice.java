package com.github.yeriomin.andtest.core;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

public class AnswerMultipleChoice extends Answer {

    protected Set<Integer> answer = new HashSet<>();

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

    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put(JSON_PROPERTY_VALUE, new JSONArray(get()));
        return object;
    }

}
