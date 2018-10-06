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

        // Let's set our ShorteningTextView programmatically:

        val sampleTexts = resources.getStringArray(R.array.example_strings)

        // Notice the `.toList()` since `texts` is a List<String> but Resources.getStringArray
        // returns, of course, an array.
        shorteningProgrammaticTextView.texts = sampleTexts.toList()

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
