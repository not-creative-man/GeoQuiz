package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mNextButton: ImageButton
    private lateinit var mPrevButton: ImageButton
    private lateinit var mQuestionTextView: View

    private val mQuestionBank = arrayOf<Question>(
        Question(R.string.question_australia, true),
        Question(R.string.question_africa, false),
        Question(R.string.question_asia, true),
        Question(R.string.question_americas, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
    )

    private var mCurrentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mQuestionTextView = findViewById<TextView>(R.id.question_text_view)
        mTrueButton = findViewById(R.id.true_button)
        mFalseButton = findViewById(R.id.false_button)
        mNextButton = findViewById(R.id.next_button)
        mPrevButton = findViewById(R.id.prev_button)

        updateQuestion()

        mTrueButton.setOnClickListener {
            checkAnswers(true)
        }
        mFalseButton.setOnClickListener {
            checkAnswers(false)
        }
        mNextButton.setOnClickListener{
            updateQuestion()
        }
        mQuestionTextView.setOnClickListener{
            updateQuestion()
        }
        mPrevButton.setOnClickListener{
            previousQuestion()
        }


    }

     private fun updateQuestion(){
        mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
        val question = mQuestionBank[mCurrentIndex].mTextResId
        (mQuestionTextView as TextView?)?.setText(question)
    }

    private fun previousQuestion(){
        mCurrentIndex = if(mCurrentIndex == 0) 6 else (mCurrentIndex - 1)
        val question = mQuestionBank[mCurrentIndex].mTextResId
        (mQuestionTextView as TextView?)?.setText(question)
    }

    private fun checkAnswers(userPressedTrue: Boolean){
        if(userPressedTrue == mQuestionBank[mCurrentIndex].mAnswerTrue){
            Toast.makeText(this@MainActivity, R.string.correct_toast, Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this@MainActivity, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
        }
    }
}

