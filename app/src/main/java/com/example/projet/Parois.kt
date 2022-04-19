package com.example.projet

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Color



class Parois(x1: Float, y1: Float, x2: Float, y2: Float): Blocs(x1, y1, x2, y2){

    fun draw(canvas: Canvas?) {
        paint.color = Color.BLACK
        canvas?.drawRect(r,paint)
    }


}