package com.example.projet

import android.graphics.*

class Parois(x1: Float, y1: Float, x2: Float, y2: Float): Blocs(x1, y1 ,x2,y2){
    override var color = Color.rgb(107, 50, 187)

    override fun draw(canvas: Canvas) {
        BlocPaint.color = color
        canvas.drawRect(bloc, BlocPaint)
    }



}