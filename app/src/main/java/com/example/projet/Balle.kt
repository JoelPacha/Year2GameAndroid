package com.example.projet
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Balle (x:Float,y: Float, var diametre : Float) : Bouge {
    var r = RectF(x, y, x + diametre, y + diametre)
    val random = Random()
    val paint = Paint()


    var dx: Int
    var dy: Int

    init {
        if (random.nextDouble() > 0.5) dx = 1 else dx = -1
        if (random.nextDouble() < 0.5) dy = 1 else dy = -1
    }


    fun draw(canvas: Canvas?) {
        paint.color =  Color.argb(
            255, random.nextInt(256),
            random.nextInt(256), random.nextInt(256)
        )
        canvas?.drawOval(r, paint)
    }

    override fun bouge(lesParois: Array<Parois>) {
        r.offset(5.0F * dx, 5.0F * dy)
        for (paroi in lesParois) {
            paroi.reactionBalle()
        }
    }

    fun changeDirection() {


    }

}






