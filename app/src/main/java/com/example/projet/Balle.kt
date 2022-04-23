package com.example.projet
import android.graphics.Color

//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Balle( x:Float, y: Float, diametre: Float): Ovni(x, y, diametre)   {

    override var dx = 0f
    override var dy = 0f
    override var VitesseOvni = 0f
    override val color = Color.BLUE

    init {
        if (random.nextDouble() > 0.5) dx = 1f else dx = -1f
        if (random.nextDouble() < 0.5) dy = 1f else dy = -1f
    }

    fun changeDirection(direction: Boolean) { // la fonction change la vitesse vertical si direction ==true
        if (direction) {
            this.dy = -dy
        }
        else {
            this.dx = -dx
        }
        r.offset(3.0F*dx, 3.0F*dy)
    }

}