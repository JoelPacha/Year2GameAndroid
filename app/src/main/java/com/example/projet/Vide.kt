package com.example.projet
import android.graphics.*

import android.graphics.Color

class Vide(x1:Float, y1:Float, x2:Float, y2:Float): Blocs(x1, y1, x2, y2) {
    override var color = Color.BLACK

    fun Reactionballe(b: Balle) {
        if (RectF.intersects(b.r, this.bloc)){
            b.disparait()
            b.reset()
        }
    }

}