package com.github.yeriomin.andtest.core;

import org.json.JSONException;
import org.json.JSONObject;

abstract public class Question extends JSONObject {

    public static final String TYPE_MC = "multipleChoice";
    public static final String TYPE_OE = "openEnded";
    
    private static final String JSON_PROPERTY_TYPE = "type";
    private static final String JSON_PROPERTY_QUESTION = "question";
    private static final String JSON_PROPERTY_EXPLANATION = "explanation";

    protected String type;
    private String question;
    private String explanation;
    private Object answer;

    protected Question(JSONObject jsonQuestion) throws JSONException {
        super(jsonQuestion);

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

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }

    abstract public boolean isCorrect();
    abstract public boolean hasAnswer();
}
