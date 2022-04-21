package com.example.projet
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Balle(var x:Float, var y: Float, var diametre : Float, val view: DrawingView)   {
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


    fun appear(){
        this.x = DrawingView.screenwidth
    }
    fun draw(canvas: Canvas?) {
        paint.color =  Color.argb(
            255, random.nextInt(256),
            random.nextInt(256), random.nextInt(256)
        )
        canvas?.drawOval(r, paint)
    }

    fun refresh( LesBlocs: Array<Blocs>){
        r.offset(5.0F * dx, 5.0F * dy)
        for (p in LesBlocs) {
            p.Reactionballe(this)
        }
    }



    fun changeDirection(direction: Boolean) {
        if (direction) {
            this.dy = -dy
        }
        else {
            this.dx = -dx
        }
        r.offset(3.0F*dx, 3.0F*dy)
    }

}






