package com.example.projet
import android.graphics.Color
import android.view.MotionEvent

//import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.*

class Balle(x:Float, y: Float, diametre: Float, var vie:Int): Ovni(x, y, diametre) {

    override var dx = 0f  //coefficient de direction en x
    override var dy = 0f  //coefficient de direction en y
    override var VitesseOvni = 500f  //vitesse de la balle initiale
    override var color = Color.rgb(38,98,235)  //couleur de la balle
    var px = 0f //
    var py = 0f //
    var vitesse_initiale = VitesseOvni //permet de stocker la vitesse initale car celle-ci est modifier au fil du jeu

    fun disparait() {   //replace la balle à sa position initiale et décremente d'une unité la variable vie
        r.set(x, y, x + diametre, y + diametre)  //set la position
        this.vie -= 1 // décremente la vie d'une unité

    }

    fun reset() { //replace la balle à sa position initiale
        VitesseOvni = vitesse_initiale  //reprend la vitesse initale de la balle
        this.dx = this.rand() //coefficient dx valant 1 ou -1 grâce à la fonction rand() de la classe Ovni
        this.dy = 1f //coefficient dy en direction du vide
        r.set(x, y, x + diametre, y + diametre) // set la position
    }

    fun launch (vx: Float, vy: Float){
        this.dx = vx
        this.dy = vy
        this.color = Color.rgb(55,142,191)
    }

    fun stopBalle (p: Plateforme){
        this.dx = 0f
        this.dy = 0f
        this.posy = p.ytop-this.diametre-5
        this.r.set(posx, posy, posx+diametre, posy+diametre)

    }

    fun throwEvent(e: MotionEvent){
        val action = e.action
        when(action){
            MotionEvent.ACTION_DOWN -> {      //repère le moment où le doigt touche l'écran
                px = e.rawX-this.posx
                py = e.rawY-this.posy
                var cosalpha = px/sqrt(px.pow(2)+py.pow(2))
                var sinalpha = py/sqrt(px.pow(2)+py.pow(2))
                this.launch(1.5f*cosalpha, 1.5f*sinalpha)
            }
        }
    }




}










