package com.github.yeriomin.andtest.core;

import org.json.JSONException;
import org.json.JSONObject;

public class AnswerOpenEnded extends Answer {

    protected String answer = "";

    {
        type = Question.TYPE_OE;
    }

    public AnswerOpenEnded() {
    }

    public AnswerOpenEnded(String answer) {
        set(answer);
    }

    public String get() {
        return answer;
    }

    public void set(String answer) {
        this.answer = answer;
    }

    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put(JSON_PROPERTY_VALUE, get());
        return object;
    }

    public void fill(String jsonString) throws JSONException {
        JSONObject object = new JSONObject(jsonString);
        if (!object.has(JSON_PROPERTY_VALUE)) {
            throw new JSONException("No answer container in JSON");
        }
        set(object.getString(JSON_PROPERTY_VALUE));
    }
}
