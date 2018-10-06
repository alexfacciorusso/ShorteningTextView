package com.alexfacciorusso.shorteningtextview.sample

import android.content.res.Resources
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val texts = listOf(
                "Very very very very very very very very very very very very very long text.",
                "Very very very very very very very long text.",
                "Very very very long text.",
                "Not long text.",
                "Text.")

        shorteningTextView.texts = texts
        shorteningTextView2.texts = texts
        shorteningTextView3.texts = texts
        dynamicShorteningTextView.texts = texts

        smallerButton.setOnClickListener {
            dynamicShorteningTextView.layoutParams =
                    (dynamicShorteningTextView.layoutParams as ConstraintLayout.LayoutParams).apply {
                        leftMargin += 90.dp
                        rightMargin += 90.dp

                        if (leftMargin < 0) leftMargin = 0
                        if (rightMargin < 0) rightMargin = 0
                    }
        }

        biggerButton.setOnClickListener {
            dynamicShorteningTextView.layoutParams =
                    (dynamicShorteningTextView.layoutParams as ConstraintLayout.LayoutParams).apply {
                        leftMargin -= 90.dp
                        rightMargin -= 90.dp

                        if (leftMargin < 0) leftMargin = 0
                        if (rightMargin < 0) rightMargin = 0
                    }
        }

        resetButton.setOnClickListener {
            dynamicShorteningTextView.layoutParams =
                    (dynamicShorteningTextView.layoutParams as ConstraintLayout.LayoutParams).apply {
                        leftMargin = 0.dp
                        rightMargin = 0.dp
                    }
        }
    }
}

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()
