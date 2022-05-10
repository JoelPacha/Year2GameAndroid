package com.example.projet

import android.graphics.RectF
import android.graphics.Color
import android.media.MediaActionSound
import android.media.MediaPlayer

class Carre(x1: Float, y1: Float, x2: Float, y2: Float, var resistance:Int): Blocs(x1, y1, x2, y2) {
    var NbreDeCollisions = 0
    override var color = Color.rgb(250, 170, 251)
    var k : Boolean = false


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
                this.color = Color.rgb(250, 170, 251) // rose
            }
            else if(NbreDeCollisions == 1){
                this.color = Color.rgb(92, 164, 147)
            }
            else{
                this.color = Color.BLACK
            }
            NbreDeCollisions += 1

        } else {
            this.disparait()

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



