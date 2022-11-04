package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mTrueButton: Button
    private lateinit var mFalseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mTrueButton = findViewById(R.id.true_button)
        mFalseButton = findViewById(R.id.false_button)

        mTrueButton.setOnClickListener {
            Log.e("", "tap")
            val toast = Toast.makeText(this@MainActivity, R.string.correct_toast, Toast.LENGTH_SHORT)
            //toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        }

        mFalseButton.setOnClickListener {
            Log.e("", "tap")
            val toast = Toast.makeText(this@MainActivity, R.string.incorrect_toast, Toast.LENGTH_SHORT)
            //toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        }
    }
}

