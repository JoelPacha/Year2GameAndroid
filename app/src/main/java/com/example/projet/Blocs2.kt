package com.example.projet

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.lang.Math.abs
import java.util.*


abstract class Blocs2(var x1: Float, var y1: Float, var x2: Float,var y2: Float) {
    val largeur = abs(x1-x2)
    val longueur = abs(y1-y2)
    var bloc = RectF(x1, y1, x2, y2)
    var OnScreen = true       // booléen qui vérifie si l'élément est à l'écran ou est détruit ( pour balle, carré, fantôme etc)
    val BlocPaint = Paint()
    abstract val color: Int   // on définis une couleur pour chaque classe différente

    /*open fun Reactionballe(b: Ovni2) {
        if (RectF.intersects(b.r,this.bloc)) {
            if (this.longueur > this.largeur) {
                b.changeDirection(false)}
            else {
                b.changeDirection(true)
            }
        }
    }*/

    open fun Reactionballe(b: Ovni2) {
        if (RectF.intersects(b.r, this.bloc)) {
            if (this.largeur > this.longueur) {
                if (y2 < 200) {
                    b.directionAbsolue(3)
                } else {
                    b.directionAbsolue(0)
                }
            }
            else {
                if (x1 < 200) {
                    b.directionAbsolue(2)
                } else {
                    b.directionAbsolue(1)
                }
            }
        }
    }

    open fun draw(canvas: Canvas) {   // dessine un rectangle "bloc" à la position des paramètres (x1, y1) et (x2,y2)
        if (this.OnScreen){
            BlocPaint.setStyle(Paint.Style.FILL) //rempli le carré de sa couleur
            BlocPaint.color = color             //choix de la couleur
            canvas.drawRect(bloc, BlocPaint)
            BlocPaint.setStyle(Paint.Style.STROKE) //permet de faire le contour
            BlocPaint.setColor(Color.BLACK)
            canvas.drawRect(bloc, BlocPaint)
        }
    }
}
