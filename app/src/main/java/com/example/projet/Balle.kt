package com.example.projet
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Balle (x:Float, y: Float, diametre : Float) : Ovni(x,y,diametre)  {

    override var color:Int = Color.RED
    override var vitesse: Float = 2.0f

   init {
       dx = -1
       dy = 0
   }


    fun bouge(LesParois: Array<Parois>,LesCarres: Array<Carre>,LesBalles : Array<Balle>) {
        r.offset(5.0F * dx, 5.0F * dy)
        for (p in LesParois) {
            p.gereBalle(this)
        }
        for (c in LesCarres) {
            c.gereBalle(this)
        }

    }

    fun rebondit(){

    }

    fun pertevie(){

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






