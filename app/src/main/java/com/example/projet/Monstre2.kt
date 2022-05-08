package com.example.projet

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

class Monstre2 (x:Float, y:Float,diametre:Float): Ovni2(x,y,diametre) {
    override var dx = 0f
    override var dy = 0f
    override var VitesseOvni = 500f
    override val color = Color.rgb(170,3,0)

    init {
        if (random.nextDouble() > 0.5) dx = 1f else dx = -1f
        if (random.nextDouble() < 0.5) dy = 1f else dy = -1f
    }


    fun reaction(array:Array<Parois2>){
        for (parois in array){
            if (RectF.intersects(parois.bloc,r)){
                if (parois.largeur > parois.longueur){
                    this.changeDirection(true)
                }
                else{
                    this.changeDirection(false)
                }
            }
        }
    }

    fun interactionmutuelle(b:Monstre2){
        if (this !== b && RectF.intersects(r,b.r)){
            this.dx = -dx
            this.dy = -dy
        }
    }


    fun mangerBalle(b: Balle2) {
        if (RectF.intersects(b.r, this.r)) {  // on évalue si la balle touche un monstre
            b.disparait()
            b.reset()

        }
    }


}