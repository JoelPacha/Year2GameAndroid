package com.example.projet

abstract class Effetmystere(x:Float,y:Float,diametre:Float) : Effets(x,y,diametre) {
    abstract var incrementation_de_vitesse : Int
    abstract var incrementation_de_taille_x : Float

    // Gere si la vitesse ou taille devient négative


    fun vitesseballe(b:Balle2){
        b.VitesseOvni += incrementation_de_vitesse

    }

    fun tailleplateforme(p : Plateforme2){
        p.x1 += incrementation_de_taille_x
        p.x2 += incrementation_de_taille_x

    }
}