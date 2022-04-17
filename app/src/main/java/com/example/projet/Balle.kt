package com.example.projet
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Balle(x:Float, y: Float, var diametre : Float) : Bouge {
    var r = RectF(x, y, x + diametre, y + diametre)
    val random = Random()
    val paint = Paint()
    var dx: Int
    var dy: Int
    var dead = false

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

    fun bouge(LesParois: Array<Parois>,LesCarres: Array<Carre>,LesBalles : Array<Balle>) {
        r.offset(5.0F * dx, 5.0F * dy)
        for (p in LesParois) {
            p.gereBalle(this)
        }
        for (c in LesCarres) {
            c.gereBalle(this)
        }

        for (b in LesBalles) {
            if (this !== b && RectF.intersects(r,b.r)) {
                b.rebondit()
                rebondit()
                break
            }
        }
    }

    fun rebondit() {
        dx = -dx
        dy = -dy
        r.offset(3.0F*dx, 3.0F*dy)
    }

    fun changeDirection(x: Boolean) {
        if (x) {
            this.dy = -dy
        }
        else {
            this.dx = -dx
        }
        r.offset(3.0F*dx, 3.0F*dy)
    }

}






