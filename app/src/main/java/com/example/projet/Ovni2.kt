package com.example.projet

import android.graphics.*
import java.util.*

abstract class Ovni2(var x:Float, var y: Float, var diametre : Float) {

    var r = RectF(x, y, x + diametre, y + diametre)
    val random = Random()
    abstract var dx : Float
    abstract var dy : Float
    abstract var VitesseOvni : Float
    var OnScreen = true // booléen qui vérifie si l'élément est à l'écran ou est détruit ( pour balle, carré, fantôme etc)
    val paint = Paint()
    abstract val color: Int



    fun draw(canvas: Canvas?) {
        paint.color = color
        if (this.OnScreen){
            canvas?.drawOval(r, paint)
        }
    }



}