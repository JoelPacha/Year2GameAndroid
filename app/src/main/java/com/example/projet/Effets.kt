package com.example.projet

import android.graphics.*
import java.util.*

abstract class Effets (var x1: Float, var y1 : Float, var x2 : Float, var y2:Float) {
    abstract var incrementation_de_vitesse : Int
    abstract var incrementation_de_taille_x : Float

    var r = RectF(x1,y1,x2,y2)
    var random = (0..1).random()

    abstract var OnScreen : Boolean // booléen qui vérifie si l'élément est à l'écran ou est détruit ( pour balle, carré, fantôme etc)
    abstract val color: Int

    val paint = Paint()

    fun disparait(){
        this.OnScreen = false
        random = (0..1).random()
    }

    fun vitesseballe(b:Balle2){
        if (b.VitesseOvni < 0){
                b.VitesseOvni = 500f
        }
        else{
            b.VitesseOvni += incrementation_de_vitesse
        }

    }

    fun tailleplateforme(p : Plateforme2){
        if (p.largeur<=0){
                p.xg = 200f
                p.xd = 200f   // mettre valeur initiale du drawingView
        }
        else{
            p.xg -=incrementation_de_taille_x
            p.xd +=incrementation_de_taille_x
            p.largeur+= incrementation_de_taille_x
        }
        p.set(p.xg,p.y1,p.xd,p.y2)
    }



    fun ReactionBalle(b: Balle2,p:Plateforme2) {
        if (this.OnScreen){
            if (RectF.intersects(r,b.r)){
                if (random == 1){
                    vitesseballe(b)
                }
                else {
                    tailleplateforme(p)
                }
                this.disparait()
            }
        }
    }


    fun draw(canvas: Canvas?) {
        paint.setStyle(Paint.Style.FILL)
        paint.color = color
        if (this.OnScreen) {
            canvas?.drawRect(r, paint)
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK)
            canvas?.drawRect(r, paint)
        }
    }

}