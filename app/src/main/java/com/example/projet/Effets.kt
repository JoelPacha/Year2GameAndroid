package com.example.projet

import android.graphics.*
import java.util.*

abstract class Effets (var x: Float, var y : Float, val diametre: Float) {
    abstract var incrementation_de_vitesse : Int
    abstract var incrementation_de_taille_x : Float

    var random = (0..1).random()
    var r = RectF(x, y, x + diametre, y + diametre)
    var init = 0

    abstract var OnScreen : Boolean // booléen qui vérifie si l'élément est à l'écran ou est détruit ( pour balle, carré, fantôme etc)
    abstract val color: Int
    val paint = Paint()

    fun disparait(){
        this.OnScreen = false
        init += 1

       // this.x = 9999f
        //this.y = 9999f

        //r.set(x,y,x+diametre,y+diametre)
    }


    fun vitesseballe(b:Balle2){
        if (this.init <= 1){
            if (b.VitesseOvni < 0){
                b.VitesseOvni = 500f
            }
            else{
                b.VitesseOvni += incrementation_de_vitesse
            }
        }


    }

    fun tailleplateforme(p : Plateforme2){
        if (this.init <= 1){
            if (p.x1 <= 0 || p.x2 <=0){
                p.x1 = 200f
                p.x2 = 200f   // mettre valeur initiale du drawingView
            }
            else{
                p.x1 -= incrementation_de_taille_x
                p.x2 += incrementation_de_taille_x
            }

            p.set(p.x1,p.y1,p.x2,p.y2)
        }
    }


    fun ReactionBalle(b: Balle2,p:Plateforme2) {
        if (RectF.intersects(r,b.r)){
            this.disparait()

            if (random == 0){
                vitesseballe(b)
            }
            else {
                tailleplateforme(p)
            }


        }
    }


    fun draw(canvas: Canvas?) {
        paint.setStyle(Paint.Style.FILL)
        paint.color = color
        if (this.OnScreen) {
            canvas?.drawOval(r, paint)
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.BLACK)
            canvas?.drawOval(r, paint)
        }
    }

}