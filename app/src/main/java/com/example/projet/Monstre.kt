package com.example.projet

import android.graphics.Color
import android.graphics.RectF

class Monstre (x:Float, y:Float, diametre:Float): Ovni(x,y,diametre) {
    override var dx = rand()
    override var dy = rand()
    override var VitesseOvni = 500f
    override val color = Color.rgb(170,3,0)




    fun reaction(array:Array<Parois>){
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

    fun interactionmutuelle(b:Monstre){
        if (this !== b && RectF.intersects(r,b.r)){
            this.dx = -dx
            this.dy = -dy
        }
    }


    fun mangerBalle(b: Balle) {
        if (RectF.intersects(b.r, this.r)) {  // on évalue si la balle touche un monstre
            b.disparait()
            b.reset()

        }
    }


}