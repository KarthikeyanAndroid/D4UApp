package com.oviesmarterware.ovie.utils

import android.content.Context
import android.graphics.Typeface
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Karthikeyan on 27-01-2021.
 */
object FontUtils {

    fun setBlackFonts(
        context: Context,
        textViews: Array<TextView>
    ) {
        val typeface = Typeface.createFromAsset(context.assets, "fonts/Avenir-Black.otf")
        for (textView in textViews) {
            textView.setTypeface(typeface)
        }
    }

    fun setMediumFonts(
        context: Context,
        textViews: Array<TextView>
    ) {
        val typeface = Typeface.createFromAsset(context.assets, "fonts/Avenir-Medium.ttf")
        for (textView in textViews) {
            textView.setTypeface(typeface)
        }
    }


    fun setTextInputLayoutMediumFont(context: Context, textInputLayouts: Array<TextInputLayout>) {
        for (textInputLayout in textInputLayouts) {
            val typeface = Typeface.createFromAsset(context.assets, "fonts/Avenir-Medium.ttf")
            textInputLayout.setTypeface(typeface);
        }
    }
    fun setTextInputLayoutBlackFont(context: Context, textInputLayouts: Array<TextInputLayout>) {
        for (textInputLayout in textInputLayouts) {
            val typeface = Typeface.createFromAsset(context.assets, "fonts/Avenir-Black.otf")
            textInputLayout.setTypeface(typeface);
        }
    }

}