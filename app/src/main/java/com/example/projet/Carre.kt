package com.example.projet

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Color

class Carre(x1: Float, y1: Float, x2: Float, y2: Float, val resistance: Int): Blocs(x1, y1, x2, y2){
    var NbreDeCollisions = 0


    override fun Reactionballe(b: Balle) {
        if (NbreDeCollisions < resistance - 1) {
            NbreDeCollisions += 1
            if (RectF.intersects(this.bloc, b.r)) {
                if (b.x == x1 || b.x == x2 || b.x + b.diametre == x1 || b.x + b.diametre == x2) {
                    b.dx -= b.dx
                }
                if (b.y == y1 || b.y == y2 || b.y + b.diametre == y1 || b.y + b.diametre == y2) {
                    b.dy -= b.dy
                }
            }
        }
        else if(NbreDeCollisions==resistance){
            NbreDeCollisions+=1
            this.disparait()
        }
    }

    override fun draw(canvas: Canvas?) {
        paint.color = Color.GRAY
        canvas?.drawRect(r,paint)
    }



}