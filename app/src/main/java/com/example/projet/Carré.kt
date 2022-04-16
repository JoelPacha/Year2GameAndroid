package com.example.projet

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.RectF

class carré(x1: Float, y1: Float, x2: Float, y2: Float): Blocs(x1, y1, x2, y2){
    override fun reactionBalle(b: Balle) {
        if (RectF.intersects(this.r,b.r )){
            if (b.dx ==0 ){
                b.changeDirection("vertical")
            }
            else if (b.dy ==0){
                b.changeDirection("horizontal")
            }
        }
    }

    override fun drawbloc(canvas: Canvas) {
        val this.paint.color = Color.

    }


}