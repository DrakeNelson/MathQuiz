package com.drakenelson.mathquiz;

public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private int correctString;
    private int incorrectString;

    public Question(int textResId, boolean answerTrue, int answerCorResId, int answerFalseResId) {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        correctString=answerCorResId;
        incorrectString=answerFalseResId;
    }

    public int getCorrectString() {
        return correctString;
    }

    public void setCorrectString(int correctString) {
        this.correctString = correctString;
    }

    public int getIncorrectString() {
        return incorrectString;
    }

    public void setIncorrectString(int incorrectString) {
        this.incorrectString = incorrectString;
    }

    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }
}
