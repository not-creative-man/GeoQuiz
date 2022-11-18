package com.example.quiz

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.ViewAnimator

class CheatActivity : AppCompatActivity() {

    private val EXTRA_ANSWER_IS_TRUE = "EXTRA_ANSWER_IS_TRUE"
    private var mAnswerIsTrue: Boolean = false
    private lateinit var mAnswerTextView: TextView
    private lateinit var mShowAnswerButton: Button
    private var EXTRA_ANSWER_SHOWN = "qwerty"
    private lateinit var apiVersion: TextView

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
        apiVersion = findViewById(R.id.api_version_for_cheat)
        mShowAnswerButton.setOnClickListener{
            if (mAnswerIsTrue)
                mAnswerTextView.setText(R.string.true_button)
            else
                mAnswerTextView.setText(R.string.false_button)
            setAnswerShownResult(true)

            val cx = mShowAnswerButton.width / 2
            val cy = mShowAnswerButton.height / 2

            val radius = mShowAnswerButton.width
            var anim = ViewAnimationUtils.createCircularReveal(mShowAnswerButton, cx, cy,
                radius.toFloat(), 0F)
            anim.addListener(object: AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    mShowAnswerButton.visibility = View.INVISIBLE
                }
            })
            anim.start()
        }

        apiVersion.text = "${apiVersion.text} ${Build.VERSION.SDK_INT}"
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean){
        val data = Intent()
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(RESULT_OK, data)
    }
}