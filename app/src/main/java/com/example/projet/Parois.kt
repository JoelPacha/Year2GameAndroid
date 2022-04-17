package com.example.projet

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Color



class Parois(x1: Float, y1: Float, x2: Float, y2: Float): Blocs(x1, y1, x2, y2){
    val r = RectF(x1,y1,x2,y2)
    val paint = Paint()


    override fun drawbloc(canvas: Canvas) {
        paint.color = Color.BLACK
        canvas.drawRect(r,paint)
    }

    fun gereBalle(b: Balle) {
        if (RectF.intersects(r,b.r)) {
            if (r.width()>r.height()) {
                b.changeDirection()(true)
            }
            else {
                b.changeDirection()(false)
            }
        }
    }

}