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
    private HashSet<Integer> answer;

    public ArrayList<String> getChoices() {
        return choices;
    }

    public HashSet<Integer> getCorrect() {
        return correct;
    }

    public HashSet<Integer> getAnswer() {
        return answer;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }

    public void setCorrect(HashSet<Integer> correct) {
        this.correct = correct;
    }

    public void setAnswer(HashSet<Integer> answer) {
        this.answer = answer;
    }

    public QuestionMultipleChoice() {
        super();

        this.setAnswer(new HashSet<Integer>());
        this.type = Question.TYPE_MC;
    }

    public QuestionMultipleChoice(Map map) throws JSONException {
        super(map);

        this.setAnswer(new HashSet<Integer>());
        this.type = Question.TYPE_MC;
        if (!map.containsKey(JSON_PROPERTY_CHOICES)) {
            throw new JSONException("choices field missing");
        }
        Iterable choices = (Iterable) map.get(JSON_PROPERTY_CHOICES);
        this.choices = new ArrayList<>();
        for (Object item: choices) {
            this.choices.add((String) item);
        }
        if (!map.containsKey(JSON_PROPERTY_CORRECT)) {
            throw new JSONException("correct field missing");
        }
        Iterable correct = (Iterable) map.get(JSON_PROPERTY_CORRECT);
        this.correct = new HashSet<>();
        for (Object item: correct) {
            this.correct.add((Integer) item);
        }
    }

    public JSONObject toJSONObject () {
        JSONObject object = super.toJSONObject();
        object.put(JSON_PROPERTY_CHOICES, this.choices);
        object.put(JSON_PROPERTY_CORRECT, this.correct);
        return object;
    }

    public boolean isCorrect() {
        return this.correct.equals(this.getAnswer());
    }

    public boolean hasAnswer() {
        return null != this.getAnswer() && this.getAnswer().size() > 0;
    }

}
