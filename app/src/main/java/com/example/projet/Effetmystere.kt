package com.example.projet

import android.graphics.RectF

abstract class Effetmystere(x:Float,y:Float,diametre:Float) : Effets(x,y,diametre) {
    abstract var incrementation_de_vitesse : Int
    abstract var incrementation_de_taille_x : Float

    var random = (0..1).random()


    fun vitesseballe(b:Balle2){
        if (b.VitesseOvni < 0){
            b.VitesseOvni = 100f
        }
        b.VitesseOvni += incrementation_de_vitesse

    }

    fun tailleplateforme(p : Plateforme2){
        if (p.x1 <= 0 || p.x2 <=0){
            p.x1 = 200f
            p.x2 = 200f   // mettre valeur initiale
        }

        p.x1 -= incrementation_de_taille_x
        p.x2 += incrementation_de_taille_x
    }


    override fun ReactionBalle(b: Balle2,p:Plateforme2) {
        if (RectF.intersects(r,b.r)){
            this.OnScreen = false
            if (random > 0.5){
                vitesseballe(b)
            }
            else {
                tailleplateforme(p)
            }
        }
    }
}