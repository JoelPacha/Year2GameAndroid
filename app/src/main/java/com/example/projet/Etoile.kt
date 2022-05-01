package com.example.projet

import android.graphics.Color

class Etoile (x:Float,y:Float,diametre:Float) : Effets(x,y,diametre){
    override var OnScreen = true
    override val color = Color.YELLOW

    fun niveausuivant(){
    }


}