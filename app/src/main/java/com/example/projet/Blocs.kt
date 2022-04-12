package com.example.projet

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.lang.Math.abs

abstract class Blocs(x1: Float,y1: Float,x2: Float, y2: Float){
    val largeur = abs(x2-x1)
    val hauteur = abs(y2-y1)

    val r = RectF(x1, y1, x2, y2)
    val paint = Paint()
    abstract fun reactionBalle(b: Balle)
    fun drawbloc(canvas: Canvas, couleur:  ){
        paint.color = Color.
    }

}