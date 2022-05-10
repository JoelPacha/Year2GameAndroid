package com.example.projet

import android.graphics.Color

class Malus(x1: Float,y1 : Float, x2 : Float, y2:Float) : Effets(x1,y1 , x2 , y2){
    override var OnScreen = true
    override val color = Color.rgb(247,165,31)
    override var incrementation_de_taille_x = -50f
    override var incrementation_de_vitesse = -100

}