package com.example.projet

import android.graphics.Color

class Vie (x:Float,y:Float,diametre:Float,var vie : Int): Effets(x,y,diametre){
    override var OnScreen = true
    override val color = Color.RED


    fun ajoutevie(){
        vie += 1
    }

    fun deletevie(){
        vie -= 1
    }


}