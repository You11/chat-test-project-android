package ru.you11.prototypechattestapp

import android.graphics.Color

data class User(
    val name: String,
    val color: Int
) {
    companion object {
        fun getColorFromDb(colorString: String): Int {
            return Color.argb(255,
                colorString.substring(0, 3).toInt(),
                colorString.substring(3, 6).toInt(),
                colorString.substring(6, 9).toInt())
        }
    }
}