package com.example.quiz

class Question(textResId: Int, answerTrue: Boolean) {
    private var mTextResId: Int
        get(): Int {
            return mTextResId
        }
        set(value: Int) {
            mTextResId = value
        }
    private var mAnswerTrue: Boolean
        get(): Boolean {
            return mAnswerTrue
        }
        set(value) {
            mAnswerTrue = value
        }

    init {
        mTextResId = textResId
        mAnswerTrue = answerTrue
    }
}