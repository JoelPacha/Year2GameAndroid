package com.example.projet

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.Color

class Carre2(x1: Float,y1: Float,x2: Float, y2: Float,var resistance:Int): Blocs2(x1, y1, x2, y2) {
    var NbreDeCollisions = 0
    override val color = Color.GREEN

    fun disparait() {
        this.OnScreen = false
    }

    override fun Reactionballe(b: Ovni2) {
        if (RectF.intersects(this.bloc, b.r)) {                  // vérifie d'abord s'il y a intersection entre les rectangles qui définissent la balle et le carré

            if (NbreDeCollisions < resistance  ) {               // vérifie si on a touché au préalable le carré moins de fois qu'il ne peut résister
                NbreDeCollisions += 1                            // incrémente le nombre de fois qu'on l'a touché
                if (b.x >=x2 || b.x + b.diametre <= x1 ) {       // vérifie si on est entrain de toucher une paroie verticale
                    b.changeDirection(false)             // inverse la vitesse en x de la balle
                }
                else if ( b.y <= y2 || b.y + b.diametre >= y1 ) {// vérifie si c'est une paroie horizontale (!!! on est obligé d'utiliser if et pas elseif sinon les deux test if sont validés pour toute collision)
                    b.changeDirection(true)              // inverse la vitesse en y de la balle
                }
            }
            else if (NbreDeCollisions == resistance) {           // si on a déjà touché le nombre max de fois le carré
                NbreDeCollisions += 1
                this.disparait() //                              // fait disparaître le carré
            }
        }
    }

}


    /* override fun draw(canvas: Canvas) {
        BlocPaint.color = color
        if (this.OnScreen == true){
            canvas.drawRect(bloc, BlocPaint)                        // sert a rien car deja definit dans bloc2
        }
    }

     */




