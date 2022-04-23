package com.example.projet

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.RectF

class Monstre2 (x:Float, y:Float,diametre:Float): Ovni2(x,y,diametre) {
    override var dx = 0f
    override var dy = 0f
    override var VitesseOvni = 0f
    override val color = Color.RED

    init {
        if (random.nextDouble() > 0.5) dx = 1f else dx = -1f
        if (random.nextDouble() < 0.5) dy = 1f else dy = -1f
    }

    fun mangerBalle(b: Balle){
        if(RectF.intersects(b.r, this.r)){  // on évalue si la balle touche une paroie verticale
            b.disparait()
        }
    }

    fun verifcontactmutuelle(objet : Array<Monstre2>) : Array<Monstre2> {     // verifie si on a interaction entre chaque monstre
        var boolean = false

        lateinit var nouveaux_monstres : Array<Monstre2>

        // verif du contact entre chaque monstres de la liste objet

        // si contact = true  alors , creer boucle while qui cree objet monstre jusqu'a ce que intersect = false

        return nouveaux_monstres

    }


    fun verifcontactbloc(objet: Monstre2) : Monstre2{
        lateinit var nouveaux_monstres : Monstre2

        // verif du contact entre chaque monstres avec chaque bloc

        // si contact = true  alors , creer boucle while qui cree objet monstre jusqu'a ce que intersect = false


        return nouveaux_monstres
    }

}