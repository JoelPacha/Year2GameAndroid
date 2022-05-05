package com.example.projet
import android.graphics.Color

//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Balle2( x:Float, y: Float, diametre: Float,var vie:Int): Ovni2(x, y, diametre)   {

    override var dx = 2f
    override var dy = 1f
    override var VitesseOvni =500f
    override val color = Color.BLUE

    var vitesse_initiale = VitesseOvni

    override fun disparait(){
        this.OnScreen = false
        this.vie -= 1
        this.dx = 0f               // empêche que la balle ne continue à bouger sans qu'on la voie et casse les carrés
        this.dy= 0f
        this.x = 9999f
        this.y = 9999f
        r.set(x,y,x+diametre,y+diametre)
    }


    fun reset(){
        VitesseOvni = vitesse_initiale
        r.set(x,y,x+diametre,y+diametre)

    }








}