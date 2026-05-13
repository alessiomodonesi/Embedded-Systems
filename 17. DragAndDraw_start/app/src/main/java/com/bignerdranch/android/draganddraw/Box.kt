package com.bignerdranch.android.draganddraw

import android.graphics.PointF

class Box(private val start: PointF) {

    var end: PointF = start

    // TODO: complete the class by writing getters for left, right, top, bottom
    // and removing the fake initializations
    val left: Float = 0.0f

    val right: Float = 1.0f

    val top: Float = 0.0f

    val bottom: Float = 1.0f

}
