package com.example.projet
import android.graphics.Color

//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Balle2( x:Float, y: Float, diametre: Float,var vie:Int): Ovni2(x, y, diametre) {

    override var dx = 1f
    override var dy = -1f
    override var VitesseOvni = 500f
    override val color = Color.BLUE

    var vitesse_initiale = VitesseOvni

    fun disparait() {
        r.set(x, y, x + diametre, y + diametre)
        this.vie -= 1
    }

    fun reset() {
        VitesseOvni = vitesse_initiale
        this.dx = 1f
        this.dy = 1f
        r.set(x, y, x + diametre, y + diametre)
    }

}










