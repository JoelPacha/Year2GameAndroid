package com.example.projet

import android.graphics.Color
import android.graphics.RectF

class Monstre (x:Float, y:Float,diametre:Float, view: DrawingView): Ovni(x,y,diametre, view) {
    override var dx = 0f
    override var dy = 0f
    override var VitesseOvni = 0f
    override val color = Color.BLUE

    init {
        if (random.nextDouble() > 0.5) dx = 1f else dx = -1f
        if (random.nextDouble() < 0.5) dy = 1f else dy = -1f
    }

    fun mangerBalle(b: Balle){
        if(RectF.intersects(b.r, this.r)){  // on évalue si la balle touche une paroie verticale
            b.disparait()
        }
    }
}