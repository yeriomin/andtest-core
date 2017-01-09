package com.github.yeriomin.andtest.core;

abstract public class Answer implements Jsonable {

    protected final static String JSON_PROPERTY_VALUE = "a";

    protected String type;

    abstract public void fill(String jsonString);
    abstract public boolean isEmpty();

    public static Answer of(String type) throws Exception {
        if (type.equals(Question.TYPE_MC)) {
            return new AnswerMultipleChoice();
        } else if (type.equals(Question.TYPE_OE)) {
            return new AnswerOpenEnded();
        } else {
            throw new Exception("Unknown type: " + type);
        }
    }

    public String getType() {
        return type;
    }

    public String toJSONString() {
        return this.toJSONObject().toString();
    }
}
