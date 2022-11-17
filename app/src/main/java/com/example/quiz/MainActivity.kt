package com.example.quiz

import android.app.Activity
import android.content.ClipData.newIntent
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.activity.result.ActivityResult
import java.io.DataOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button
    private lateinit var mNextButton: ImageButton
    private lateinit var mPrevButton: ImageButton
    private lateinit var mQuestionTextView: View
    private val TAG = "QuizActivity"
    private val KEY_INDEX = "index"

    private val mQuestionBank = arrayOf<Question>(
        Question(R.string.question_australia, true),
        Question(R.string.question_africa, false),
        Question(R.string.question_asia, true),
        Question(R.string.question_americas, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
    )

    private lateinit var mCheatButton: Button

    private var mCurrentIndex = 0
    private var REQUEST_CODE_CHEAT = 0
    private var mIsCheater: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0)

        Log.d(TAG, "onCreate()")

        mQuestionTextView = findViewById<TextView>(R.id.question_text_view)
        mTrueButton = findViewById(R.id.true_button)
        mFalseButton = findViewById(R.id.false_button)
        mNextButton = findViewById(R.id.next_button)
        mPrevButton = findViewById(R.id.prev_button)
        mCheatButton = findViewById(R.id.cheat_button)

        updateQuestion()

        mTrueButton.setOnClickListener {
            checkAnswers(true)
        }
        mFalseButton.setOnClickListener {
            checkAnswers(false)
        }
        mNextButton.setOnClickListener {
            mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.size
            updateQuestion()
        }
        mQuestionTextView.setOnClickListener {
            updateQuestion()
        }
        mPrevButton.setOnClickListener {
            mCurrentIndex = if (mCurrentIndex == 0) 6 else (mCurrentIndex - 1)
            updateQuestion()
        }
        mCheatButton.setOnClickListener {
            val answerIsTrue = mQuestionBank[mCurrentIndex].mAnswerTrue
            val intent = CheatActivity.newIntent(this, answerIsTrue)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }
    }

    private fun updateQuestion() {
        val question = mQuestionBank[mCurrentIndex].mTextResId
        (mQuestionTextView as TextView?)?.setText(question)
        changeClickable(mTrueButton, false)
        changeClickable(mFalseButton, false)
        mIsCheater = false
    }

    private fun checkAnswers(userPressedTrue: Boolean) {
        changeClickable(mTrueButton, true)
        changeClickable(mFalseButton, true)
        var messageResId = if (mIsCheater)
            R.string.judgment_toast
        else if (userPressedTrue == mQuestionBank[mCurrentIndex].mAnswerTrue)
            R.string.correct_toast
        else
            R.string.incorrect_toast

        Toast.makeText(this@MainActivity, messageResId, Toast.LENGTH_SHORT).show()
    }

    fun changeClickable(button: Button, isChecking: Boolean) {
        button.isClickable = !isChecking
        if (button.isClickable) button.setBackgroundColor(getColor(R.color.purple_500))
        else button.setBackgroundColor(getColor(R.color.grey))
        Log.d("TAG", button.background.toString())
        Log.d("TAG", button.isClickable.toString())
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i(KEY_INDEX, mCurrentIndex.toString())
        outState.putInt(KEY_INDEX, mCurrentIndex)
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return
            }
            mIsCheater = CheatActivity.wasAnswerShown(data)
        }
    }
}

