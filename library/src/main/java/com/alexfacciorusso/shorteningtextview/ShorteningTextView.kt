package com.alexfacciorusso.shorteningtextview

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import kotlin.properties.Delegates

class ShorteningTextView @JvmOverloads constructor(
        context: Context? = null,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    var texts by Delegates.observable(listOf<CharSequence>()) { _, _, _ -> updateText() }

    var actualTextIndex: Int = 0
        private set

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateText()
    }

    private fun updateText() {
        if (texts.isEmpty()) {
            text = ""
            return
        }

        // TODO: add caching
        val firstFittedIndex = texts.asSequence()
                .map {
                    paint.measureText(it.toString())
                }
                .indexOfFirst { it <= measuredWidth }

        if (actualTextIndex == firstFittedIndex) return

        actualTextIndex = firstFittedIndex
        text = texts.getOrElse(firstFittedIndex) { texts.first() }
        invalidate()
    }
}