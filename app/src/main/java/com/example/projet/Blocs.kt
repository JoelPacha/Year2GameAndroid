package com.example.projet

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.lang.Math.abs
import java.util.*


abstract class Blocs(var x1: Float, var y1: Float, var x2: Float,var y2: Float) {
    val largeur = abs(x2 - x1)
    val hauteur = abs(y2 - y1)
    val random = Random()
    val bloc = RectF(x1, y1, x2, y2)
    val paint = Paint()
    abstract val color: Int

    open fun Reactionballe(b: Balle) {
        if (RectF.intersects(bloc, b.r)) {
            if(b.x ==x1 || b.x==x2 ||b.x+b.diametre==x1 ||b.x+b.diametre==x2){  // on évalue si la balle touche une paroie verticale
                b.changeDirection(false)
            }
            if(b.y ==y1 || b.y==y2 ||b.y+b.diametre==y1 ||b.y+b.diametre==y2){ // parois horizontale
                b.changeDirection(true)
            }
        }
    }



    open fun draw(canvas: Canvas?) {
        paint.color = color
        canvas?.drawRect(bloc, paint)
    }
}
