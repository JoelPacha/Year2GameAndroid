package com.example.projet

import android.graphics.Color
import android.graphics.RectF

class Monstre (x:Float, y:Float, diametre:Float): Ovni(x,y,diametre) {
    override var dx = rand()
    override var dy = rand()
    override var VitesseOvni = 200f
    override val color = Color.rgb(170,3,0)

    fun interactionmutuelle(lesMonstre: ArrayList<Monstre>){
        for (monstre in lesMonstre){
            if (this !== monstre && RectF.intersects(r,monstre.r)){
                this.dx = -dx
                this.dy = -dy
            }
        }
    }



    fun mangerBalle(b: Balle) {
        if (RectF.intersects(b.r, this.r)) {  // on évalue si la balle touche un monstre
            b.disparait()
            b.reset()

        }
    }


}