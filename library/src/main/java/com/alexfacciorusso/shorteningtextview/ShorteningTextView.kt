package com.alexfacciorusso.shorteningtextview

import android.content.Context
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.alexfacciorusso.library.R
import kotlin.properties.Delegates

/**
 * A TextView that shows the right text based on its size.
 * It chooses the text to show from the [texts] property, and changes it dynamically when its size
 * changes.
 */
class ShorteningTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {
    /**
     * The list of Strings from where the displayed text will be selected.
     */
    var texts by Delegates.observable(listOf<CharSequence>()) { _, _, _ -> updateText() }

    private var actualTextIndex: Int = 0

    init {
        context.theme?.obtainStyledAttributes(
                attrs,
                R.styleable.ShorteningTextView,
                0, 0)?.apply {
            try {
                texts = getTextArray(R.styleable.ShorteningTextView_stv_texts).orEmpty().toList()
            } finally {
                this.recycle()
            }
        }
    }

    @Deprecated("Don't use this method directly.", ReplaceWith("texts"))
    override fun setText(text: CharSequence?, type: BufferType?) {
        super.setText(text, type)
    }

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