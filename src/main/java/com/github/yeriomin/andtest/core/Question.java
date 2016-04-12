package com.github.yeriomin.andtest.core;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

abstract public class Question implements Jsonable {

    public static final String TYPE_MC = "multipleChoice";
    public static final String TYPE_OE = "openEnded";
    
    private static final String JSON_PROPERTY_TYPE = "type";
    private static final String JSON_PROPERTY_QUESTION = "question";
    private static final String JSON_PROPERTY_EXPLANATION = "explanation";

    protected String type;
    private String question;
    private String explanation;

    public Question() {

    }

    public Question(Map map) throws JSONException {
        if (!map.containsKey(JSON_PROPERTY_QUESTION)) {
            throw new JSONException("question field missing");
        }
        this.question = (String) map.get(JSON_PROPERTY_QUESTION);
        if (!map.containsKey(JSON_PROPERTY_EXPLANATION)) {
            throw new JSONException("explanation field missing");
        }
        this.explanation = (String) map.get(JSON_PROPERTY_EXPLANATION);
    }

    public static Question of(Map map) throws JSONException {
        try {
            final String type = (String) map.get(JSON_PROPERTY_TYPE);
            if (type.equals(TYPE_MC)) {
                return new QuestionMultipleChoice(map);
            } else if (type.equals(TYPE_OE)) {
                return new QuestionOpenEnded(map);
            } else {
                throw new JSONException("Unknown type: " + type);
            }
        } catch (ClassCastException e) {
            throw new JSONException("type is expected to be a String", e);
        }
    }

    public String getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String toJSONString() {
        return this.toJSONObject().toString(4);
    }

    public JSONObject toJSONObject() {
        JSONObject object = new JSONObject();
        object.put(JSON_PROPERTY_TYPE, this.type);
        object.put(JSON_PROPERTY_QUESTION, this.question);
        object.put(JSON_PROPERTY_EXPLANATION, this.explanation);
        return object;
    }

    abstract public boolean isCorrect();
    abstract public boolean hasAnswer();
}
