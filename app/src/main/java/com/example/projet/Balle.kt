package com.example.projet
import android.graphics.Color
import android.view.MotionEvent

//import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.*

class Balle(x:Float, y: Float, diametre: Float, var vie:Int): Ovni(x, y, diametre) {

    override var dx = rand()
    override var dy = rand()
    override var VitesseOvni = 400f
    override var color = Color.rgb(38,98,235)
    var px = 0f
    var py = 0f
    var alpha = 0f
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

    fun launch (vx: Float, vy: Float){
        this.dx = vx
        this.dy = vy
        this.color = Color.rgb(55,142,191)
    }

    fun stopBalle (p: Plateforme){
        this.dx = 0f
        this.dy =0f
        this.posy = p.ytop-this.diametre-5
        this.r.set(posx, posy, posx+diametre, posy+diametre)

    }

   /* fun dragEvent(e: MotionEvent) {  // fonction qui permet de bouger la plateforme en maintenant appuyé et glissant le doigt sur l'écran
        val action = e.action  // sorte d'action: un click ou un glissement
        when(action){
            MotionEvent.ACTION_DOWN -> {      //repère le moment où le doigt touche l'écran
                px = e.rawX - this.posx          // Dx la distance entre le click et le côté gauche de la plateforme
                this.r.set(posx, posy, posx+diametre, posy+diametre)
            }
            MotionEvent.ACTION_MOVE -> { // Repère le moment où on glisse
                posx= e.rawX -px                // Modifie la position de la plateforme en la glissant vers la gauche ou la droite
                this.r.set(posx, posy,posx+diametre,posy+diametre)
            }
        }
    }*/

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










