package com.example.projet

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import java.util.*

abstract class Ovni (var x:Float, var y: Float, var diametre : Float) {

    var r = RectF(x, y, x + diametre, y + diametre)
    val paint = Paint()
    val random = Random()

    abstract var dx : Int
    abstract var dy : Int
    abstract var color : Int
    abstract var vitesse : Float




    fun draw(canvas: Canvas?){
        paint.color = color
        canvas?.drawOval(r,paint)

    }

    fun bouge(){
        r.offset(vitesse*dx.toFloat(),vitesse*dy.toFloat())


    }

}