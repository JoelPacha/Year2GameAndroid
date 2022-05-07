package com.example.projet
import android.graphics.Color

//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Balle2( x:Float, y: Float, diametre: Float,var vie:Int): Ovni2(x, y, diametre) {

    override var dx = rand()
    override var dy = rand()
    override var VitesseOvni = 1100f
    override val color = Color.rgb(49,110,51)

    var vitesse_initiale = VitesseOvni

    fun disparait() {
        r.set(x, y, x + diametre, y + diametre)
        this.vie -= 1
    }

    fun reset() {
        VitesseOvni = vitesse_initiale
        this.dx = rand()
        this.dy = 1f // quand la balle spawn sa permet qu'elle aille direct en direction de la plateforme sa a plus de sens
        r.set(x, y, x + diametre, y + diametre)
    }

    fun rand() : Float{
        var value = 0f
        if (random.nextDouble() > 0.5) value = 1f else value = -1f

        return value
    }

}










