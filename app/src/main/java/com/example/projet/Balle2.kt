package com.example.projet
import android.graphics.Color
import android.view.MotionEvent

//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.sin

class Balle2( x:Float, y: Float, diametre: Float,var vie:Int): Ovni2(x, y, diametre) {

    override var dx = rand()
    override var dy = rand()
    override var VitesseOvni = 500f
    override val color = Color.rgb(55,142,191)
    var px = 0f
    var py = 0f
    var vitesse_initiale = VitesseOvni
    var alpha = 0f

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
    }

    fun stopBalle (p: Plateforme2){
        this.dx = 0f
        this.dy =0f
    }

    fun dragEvent(e: MotionEvent) {  // fonction qui permet de bouger la plateforme en maintenant appuyé et glissant le doigt sur l'écran
        val action = e.action  // sorte d'action: un click ou un glissement
        when(action){
            MotionEvent.ACTION_DOWN -> {      //repère le moment où le doigt touche l'écran
                px = e.rawX - this.posx          // Dx la distance entre le click et le côté gauche de la plateforme
                this.r.set(posx, posy, posx+diametre, posy+diametre)
            }
            MotionEvent.ACTION_MOVE -> { // Repère le moment où on glisse
                px = e.rawX - this.posx
                this.r.set(posx, posy,posx+diametre,posy+diametre)
            }
        }
    }

    fun throwEvent(e: MotionEvent){
        val action = e.action
        when(action){
            MotionEvent.ACTION_DOWN -> {      //repère le moment où le doigt touche l'écran
                px = e.rawX-this.x
                py = e.rawY-this.y
                alpha = atan(py/px)
                this.launch(cos(alpha), sin(alpha))
            }
        }
    }


    fun rand() : Float{
        var value = 0f
        if (random.nextDouble() > 0.5) value = 1f else value = -1f

        return value
    }

}










