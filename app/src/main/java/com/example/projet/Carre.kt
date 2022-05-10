package com.example.projet

import android.graphics.RectF
import android.graphics.Color
import android.media.MediaActionSound
import android.media.MediaPlayer

class Carre(x1: Float, y1: Float, x2: Float, y2: Float, var resistance:Int): Blocs(x1, y1, x2, y2) {
    var NbreDeCollisions = 0
    override var color = Color.rgb(250, 170, 251)
    var k : Boolean = false


    /*fun playmusic(){
        val mediaPlayer = MediaPlayer.create(this,R.raw.vibration)
        mediaPlayer.start()
    }
*/

    fun verifdisparition() : Boolean{  // renvoie true si le carre est effacé
        if(this.OnScreen == false){
            k = true
        }
        return k
    }

    fun disparait() {
        this.OnScreen = false
    }


    fun verifresistance() {
        if (NbreDeCollisions < resistance) {
            if (NbreDeCollisions == 0){
                this.color = Color.rgb(155,217,202)
            }
            else if(NbreDeCollisions == 1){
                this.color = Color.MAGENTA
            }
            else{
                this.color = Color.GREEN
            }
            NbreDeCollisions += 1


        } else {
            this.disparait()
            //playmusic()


        }
    }


    fun Reactionballe(b: Balle) {
        if (this.OnScreen){
            if (RectF.intersects(this.bloc, b.r)){
                    b.changeDirection(true)
                    verifresistance()
            }
        }
    }
}


/*
    override fun Reactionballe(b: Ovni2) {
        if (this.OnScreen) {
            if (RectF.intersects(this.bloc, b.r)) {
                println("contact")

                if (b.x > this.x2 || b.x + b.diametre < this.x1) {
                    b.changeDirection(false)
                    verifresistance()

                }
                if (b.y > this.y2 || b.y + b.diametre < this.y1) {
                    b.changeDirection(true)
                    verifresistance()
                }
            }
        }
    }



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

                if ( b.y <= y2 || b.y + b.diametre >= y1 ) {
                    b.changeDirection(true)
                }
                else if (b.x >=x2 || b.x + b.diametre <= x1 ) {
                    b.changeDirection(false)
                }
                this.disparait()
            }
        }
    }




    override fun Reactionballe(b: Ovni2) {
        if (RectF.intersects(this.bloc, b.r)) {                  // vérifie d'abord s'il y a intersection entre les rectangles qui définissent la balle et le carré
            println("carretouchee")
            if (NbreDeCollisions < resistance  ) {               // vérifie si on a touché au préalable le carré moins de fois qu'il ne peut résister
                NbreDeCollisions += 1 // incrémente le nombre de fois qu'on l'a touché

                if (  abs( b.x -x1 ) > abs( b.y-y1  )) {
                    b.changeDirection(false)
                }
                else if (  abs( b.x -x1 ) < abs( b.y-y1  )) {
                    b.changeDirection(true)
                }
            }


            else {           // si on a déjà touché le nombre max de fois le carré
                NbreDeCollisions += 1

                if (  abs( b.x -x1 ) > abs( b.y-y1  )) {
                    b.changeDirection(false)
                }
                else if (  abs( b.x -x1 ) < abs( b.y-y1  )) {
                    b.changeDirection(true)
                }
                this.disparait()
            }
        }
    }

}


     override fun draw(canvas: Canvas) {
        BlocPaint.color = color
        if (this.OnScreen == true){
            canvas.drawRect(bloc, BlocPaint)                        // sert a rien car deja definit dans bloc2
        }
    }



 */



