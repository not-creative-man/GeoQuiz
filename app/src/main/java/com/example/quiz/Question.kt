package com.example.quiz

class Question(textResId: Int, answerTrue: Boolean) {
    var mTextResId: Int
    var mAnswerTrue: Boolean

    init {
        mTextResId = textResId
        mAnswerTrue = answerTrue
    }
}