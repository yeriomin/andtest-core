package com.github.yeriomin.andtest.core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

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

    public QuestionMultipleChoice(JSONObject jsonQuestion) throws JSONException {
        super(jsonQuestion);

        this.setAnswer(new HashSet<Integer>());
        this.type = Question.TYPE_MC;
        JSONArray choices = jsonQuestion.getJSONArray(JSON_PROPERTY_CHOICES);
        this.choices = new ArrayList<>();
        for (int i = 0; i < choices.length(); i++){
            this.choices.add(choices.getString(i));
        }
        JSONArray correct = jsonQuestion.getJSONArray(JSON_PROPERTY_CORRECT);
        this.correct = new HashSet<>();
        for (int i = 0; i < correct.length(); i++){
            this.correct.add(correct.getInt(i));
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
