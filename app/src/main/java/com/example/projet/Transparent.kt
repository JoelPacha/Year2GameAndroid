package com.example.projet

import android.graphics.Canvas
import android.graphics.Color

class Transparent(x1:Float, y1:Float, x2:Float, y2:Float): Blocs2(x1, y1, x2, y2) {
    override val color = Color.TRANSPARENT

    override fun draw(canvas: Canvas) {
        if (this.OnScreen){
            BlocPaint.color = color
            canvas.drawRect(bloc, BlocPaint)
        }
    }
}