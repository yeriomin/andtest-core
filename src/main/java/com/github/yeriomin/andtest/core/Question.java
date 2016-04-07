package com.github.yeriomin.andtest.core;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;

abstract public class Question implements Jsonable, JSONString {

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

    public Question(JSONObject jsonQuestion) throws JSONException {
        this.question = jsonQuestion.getString(JSON_PROPERTY_QUESTION);
        this.explanation = jsonQuestion.getString(JSON_PROPERTY_EXPLANATION);
    }

    public static Question of(JSONObject jsonQuestion) throws JSONException {
        final String type = jsonQuestion.getString(JSON_PROPERTY_TYPE);
        if (type.equals(TYPE_MC)) {
            return new QuestionMultipleChoice(jsonQuestion);
        } else if (type.equals(TYPE_OE)) {
            return new QuestionOpenEnded(jsonQuestion);
        } else {
            throw new JSONException("Unknown type: " + type);
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
        return this.toJsonObject().toString(4);
    }

    public JSONObject toJsonObject () {
        JSONObject object = new JSONObject();
        object.put(JSON_PROPERTY_TYPE, this.type);
        object.put(JSON_PROPERTY_QUESTION, this.question);
        object.put(JSON_PROPERTY_EXPLANATION, this.explanation);
        return object;
    }

    abstract public boolean isCorrect();
    abstract public boolean hasAnswer();
}
