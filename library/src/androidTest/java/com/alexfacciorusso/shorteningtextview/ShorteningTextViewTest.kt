package com.alexfacciorusso.shorteningtextview

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.ViewGroup
import android.widget.LinearLayout
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ShorteningTextViewTest {

    @Test
    fun maxTextShown() {
        val appContext = InstrumentationRegistry.getTargetContext()

        // Context of the app under test.
        val testTexts = listOf(
                "Very very very very very very very very very very very very very long text.",
                "Very very very very very very very long text.",
                "Very very very long text.",
                "Not long text.",
                "Text.")

        testTexts.forEach {
            val shorteningTextView = ShorteningTextView(appContext)
            shorteningTextView.texts = testTexts
            with(shorteningTextView) {
                val textWidth = paint.measureText(it)

                layoutParams = LinearLayout.LayoutParams(textWidth.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
                measure(0, 0)
                requestLayout()

                //TODO make the test work
//                assertEquals(it, text)
            }
        }
    }
}
