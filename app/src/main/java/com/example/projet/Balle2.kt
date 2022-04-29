package com.example.projet
import android.graphics.Color

//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Balle2( x:Float, y: Float, diametre: Float): Ovni2(x, y, diametre)   {

    override var dx = 0f
    override var dy = 1f
    override var VitesseOvni = 400f
    override val color = Color.BLUE


    /*fun set(w : Float ,h :Float,d:Float){
        this.x = w
        this.y = h
        this.diametre = d

    }

    fun get() : Float{
        return this.x
    }

     */

}