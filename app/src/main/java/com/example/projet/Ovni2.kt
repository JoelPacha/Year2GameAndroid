package com.example.projet

import android.graphics.*
import java.util.*

abstract class Ovni2(var x:Float, var y: Float, var diametre : Float) {

    var r = RectF(x, y, x + diametre, y + diametre)
    val random = Random()
    abstract var dx: Float
    abstract var dy: Float
    abstract var VitesseOvni: Float
    var OnScreen = true // booléen qui vérifie si l'élément est à l'écran ou est détruit ( pour balle, carré, fantôme etc)
    val paint = Paint()
    abstract val color: Int
    var distance_frame = 0f


    open fun draw(canvas: Canvas?) {
        paint.setStyle(Paint.Style.FILL)
        paint.color = color
        if (this.OnScreen) {
            canvas?.drawOval(r, paint)
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK)
            canvas?.drawOval(r, paint)
        }
    }

    fun changeDirection(direction: Boolean,) { // la fonction change la vitesse vertical si direction ==true
        if (direction) {
            this.dy = -dy
        }
        else {
            this.dx = -dx
        }
        r.offset(1.0f*dx, 1.0f*dy)
    }

    /* open fun bouge(canvas: Canvas) {
        r.offset(5.0F*dx,5.0F*dy)
        draw(canvas)
    }

     */

    open fun bouge(FrameTime: Double){
        distance_frame = (FrameTime * VitesseOvni).toFloat()
        r.offset(dx*distance_frame, dy*distance_frame)

        println("mouvement en x" + this.x)
        println("mouvement de y" + this.y)
    }

}