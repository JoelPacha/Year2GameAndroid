package com.example.projet

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Color

class Carre(x1: Float, y1: Float, x2: Float, y2: Float): Blocs(x1, y1, x2, y2){


    override fun gereBalle(b: Balle) {
        if (RectF.intersects(this.r,b.r )){
            if (b.dx ==0 ){
                b.changeDirection(true)
            }
            else if (b.dy ==0){
                b.changeDirection(false)
            }
        }
    }

    override fun draw(canvas: Canvas?) {
        paint.color = Color.GRAY
        canvas?.drawRect(r,paint)
    }

    fun casse(){}

}