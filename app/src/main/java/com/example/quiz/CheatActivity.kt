package com.example.quiz

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class CheatActivity : AppCompatActivity() {

    private val EXTRA_ANSWER_IS_TRUE = "EXTRA_ANSWER_IS_TRUE"
    private var mAnswerIsTrue: Boolean = false
    private lateinit var mAnswerTextView: TextView
    private lateinit var mShowAnswerButton: Button
    private var EXTRA_ANSWER_SHOWN = "qwerty"

    companion object{
        fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent{
            val intent = Intent(packageContext, CheatActivity::class.java)
            intent.putExtra("EXTRA_ANSWER_IS_TRUE", answerIsTrue)
            return intent
        }
        fun wasAnswerShown(result: Intent): Boolean{
            return result.getBooleanExtra("qwerty", false)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        mAnswerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

        mAnswerTextView = findViewById(R.id.answer_text_view)
        mShowAnswerButton = findViewById(R.id.show_answer_button)
        mShowAnswerButton.setOnClickListener{
            if (mAnswerIsTrue)
                mAnswerTextView.setText(R.string.true_button)
            else
                mAnswerTextView.setText(R.string.false_button)
            setAnswerShownResult(true)
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean){
        val data = Intent()
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(RESULT_OK, data)
    }
}