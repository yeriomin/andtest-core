package com.github.yeriomin.andtest.core;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;

public class QuestionMultipleChoice extends Question {

    private static final String JSON_PROPERTY_CHOICES = "choices";
    private static final String JSON_PROPERTY_CORRECT = "correct";

    private ArrayList<String> choices;
    private HashSet<Integer> correct;

    {
        this.type = Question.TYPE_MC;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public HashSet<Integer> getCorrect() {
        return correct;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public void setCorrect(HashSet<Integer> correct) {
        this.correct = correct;
    }

    public QuestionMultipleChoice(Map map) throws JSONException {
        super(map);

        this.type = Question.TYPE_MC;
        if (!map.containsKey(JSON_PROPERTY_CHOICES)) {
            throw new JSONException("choices field missing");
        }
        try {
            Iterable<String> choices = (Iterable) map.get(JSON_PROPERTY_CHOICES);
            this.choices = new ArrayList<>();
            for (String item: choices) {
                this.choices.add(item);
            }
        } catch (ClassCastException e) {
            throw new JSONException("choices is expected to be an Iterable<String>", e);
        }
        if (!map.containsKey(JSON_PROPERTY_CORRECT)) {
            throw new JSONException("correct field missing");
        }
        try {
            Iterable<Integer> correct = (Iterable) map.get(JSON_PROPERTY_CORRECT);
            this.correct = new HashSet<>();
            for (Integer item: correct) {
                this.correct.add(item);
            }
        } catch (ClassCastException e) {
            throw new JSONException("correct is expected to be an Iterable<Integer>", e);
        }
    }

    public JSONObject toJSONObject () {
        JSONObject object = super.toJSONObject();
        object.put(JSON_PROPERTY_CHOICES, this.choices);
        object.put(JSON_PROPERTY_CORRECT, this.correct);
        return object;
    }

    public boolean isCorrect(Answer answer) {
        return answer instanceof AnswerMultipleChoice && this.correct.equals(((AnswerMultipleChoice) answer).get());
    }

}
