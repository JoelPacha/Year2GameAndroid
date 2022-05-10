package com.example.projet

import android.graphics.Color

class Malus(x1: Float,y1 : Float, x2 : Float, y2:Float) : Effets(x1,y1 , x2 , y2){
    override var OnScreen = true
    override var color = Color.rgb(25,7,58)
    override var incrementation_de_taille_x = -5f
    override var incrementation_de_vitesse = -1

    override fun tailleplateforme(p : Plateforme, w: Float){
        if (p.largeur>=130f){
            p.xg -=incrementation_de_taille_x
            p.xd +=incrementation_de_taille_x
            p.largeur+= 2*incrementation_de_taille_x
        }
        p.set(p.xg,p.ytop,p.xd,p.ybottom)
    }

}