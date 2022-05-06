package com.example.projet
import android.graphics.Color
import android.graphics.RectF

//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Balle2( x:Float, y: Float, diametre: Float,var vie:Int): Ovni2(x, y, diametre)   {

    override var dx = 1f
    override var dy = 1f
    override var VitesseOvni =200f
    override val color = Color.BLUE
    var init = 0

    var vitesse_initiale = VitesseOvni

    override fun disparait(){
        this.OnScreen = false
        this.init += 1

        if (this.init <= 1){
            this.vie -= 1
        }
        this.dy = 0f
        this.dx = 0f


        //this.x = 9999f
        //this.y = 9999f
        //r.set(x,y,x+diametre,y+diametre)
    }


    fun reset(){
        this.OnScreen = true
        VitesseOvni = vitesse_initiale

        this.dx = 1f
        this.dy = 1f
        r.set(400f,1500f,x+diametre,y+diametre)
    }








}