package com.example.projet
import android.graphics.*

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.graphics.Color
import android.view.MotionEvent
import java.lang.Math.abs

class Plateforme2(x1:Float, y1:Float, x2:Float, y2:Float): Blocs2(x1, y1, x2, y2) {
    override val color = Color.WHITE

    var Dx = 0f


    fun bouge(e: MotionEvent) {  // fonction qui permet de bouger la plateforme en maintenant appuyé et glissant le doigt sur l'écran
        val action = e.action  // sorte d'action: un click ou un glissement
        when(action){
            MotionEvent.ACTION_DOWN -> {      //repère le moment où le doigt touche l'écran
                Dx = e.rawX - this.x1          // Dx la distance entre le click et le côté gauche de la plateforme

            }
            MotionEvent.ACTION_MOVE -> {      // Repère le moment où on glisse
                x1= e.rawX -Dx                // Modifie la position de la plateforme en la glissant vers la gauche ou la droite
                x2 = x1+this.largeur
                bloc = RectF(x1, y1, x2, y2)
            }
        }
    }

    override fun Reactionballe(b: Ovni2) {            // si on prend pas en compte la collision sur le côté, les OVNI vont
        if (RectF.intersects(b.r, this.bloc)){        // rentrer et osciller dans la plateforme
            if (b.x >=x2 || b.x + b.diametre <= x1 ) {
                b.changeDirection(true)
            }
            else if ( b.y <= y2 || b.y + b.diametre >= y1 ) {
                b.changeDirection(false)
            }
        }
    }
}