package com.example.projet
import android.graphics.*

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.graphics.Color
import android.view.MotionEvent
import java.lang.Math.abs

class Plateforme2(x1:Float, y1:Float, x2:Float, y2:Float): Blocs2(x1, y1, x2, y2) {
    override val color = Color.rgb(17,61,88)
    var Dx = 0f
    var n = 0f
    var increment = 0f
    var xg = bloc.left
    var xd = bloc.right  // on utilise xg et xd pour éviter d'avoir des problèmes quand la plateforme change de taille


    fun bouge(e: MotionEvent, w: Float ) {
         // fonction qui permet de bouger la plateforme en maintenant appuyé et glissant le doigt sur l'écran
        val action = e.action  // sorte d'action: un click ou un glissement
        when(action){
            MotionEvent.ACTION_DOWN -> {      //repère le moment où le doigt touche l'écran
                Dx = e.rawX - this.xg         // Dx la distance entre le click et le côté gauche de la plateforme
                this.set(xg, y1, xd, y2)
            }
            MotionEvent.ACTION_MOVE -> { // Repère le moment où on glisse
                xg = e.rawX - Dx                // Modifie la position de la plateforme en la glissant vers la gauche ou la droite
                xd = xg + this.largeur
                this.bloquerPlateforme(w)
                }
            }
        }

    fun bloquerPlateforme( w: Float){

        if (xg > w/50f+w/100f) {
            this.set(xg, y1, xd, y2)
        }
        else if (xg<w/50f+w/100f){
            xg = w/50f+w/100f
            xd = xg + largeur
            this.set(xg, y1, xd, y2)
        }
        if (xd <w- w/50f-w/100f){
            this.set(xg,y1,xd,y2)
        }
        else if(xd>w-w/50f-w/100){
            xd = w-w/50f-w/100f
            xg = xd-largeur
            this.set(xg, y1, xd, y2)
        }
    }


    /*override fun Reactionballe(b: Ovni2) {

            if (RectF.intersects(b.r, this.bloc)) {
                b.directionAbsolue(0)
            }
    }*/

    fun Reactionballe(b: Balle2){
        if (RectF.intersects(b.r, this.bloc)) {
            b.stopBalle(this)
            b.color = Color.RED
        }
    }


    fun set(x1:Float,y1:Float,x2:Float,y2:Float){
        bloc.set(x1,y1,x2,y2)
    }


}