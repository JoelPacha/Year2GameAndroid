package com.example.projet

import android.graphics.Color

class Vie (x:Float,y:Float,diametre:Float): Effets(x,y,diametre){
    override var OnScreen = true
    override val color = Color.RED

    var vie = 3

    fun ajoutevie(){
        vie += 1
    }


}