package com.example.projet

import android.graphics.*
import java.util.*

abstract class Effets (var x: Float, var y : Float, val diametre: Float) {
    var r = RectF(x, y, x + diametre, y + diametre)

    abstract var OnScreen : Boolean // booléen qui vérifie si l'élément est à l'écran ou est détruit ( pour balle, carré, fantôme etc)
    abstract val color: Int
    val paint = Paint()

    open fun ReactionBalle(b: Balle2,p:Plateforme2){
        if (RectF.intersects(r,b.r)){
            this.OnScreen = false // INTERFACE ?
        }
    }


    fun draw(canvas: Canvas?) {
        paint.setStyle(Paint.Style.FILL)
        paint.color = color
        if (this.OnScreen) {
            canvas?.drawOval(r, paint)
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK)
            canvas?.drawOval(r, paint)
        }
    }

}