package com.example.projet

import android.graphics.*

class Parois2(x1: Float, y1: Float, x2: Float, y2: Float): Blocs2(x1, y1 ,x2,y2){
    override val color = Color.MAGENTA

    override fun draw(canvas: Canvas) {
        BlocPaint.color = color
        canvas.drawRect(bloc, BlocPaint)
    }

    fun Reactionballe(b: Ovni2) {
        if (RectF.intersects(b.r,this.bloc)) {
            if (this.longueur > this.largeur) {
                b.changeDirection(false)}
            else {
                b.changeDirection(true)
            }
        }
    }
}