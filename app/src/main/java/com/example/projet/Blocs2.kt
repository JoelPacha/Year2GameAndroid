package com.example.projet

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import java.lang.Math.abs
import java.util.*


abstract class Blocs2(var x1: Float, var y1: Float, var x2: Float,var y2: Float) {
    val largeur = x1-x2
    val random = Random()
    val bloc = RectF(x1, y1, x2, y2)
    var OnScreen = true       // booléen qui vérifie si l'élément est à l'écran ou est détruit ( pour balle, carré, fantôme etc)

    val BlocPaint = Paint()
    abstract val color: Int   // on définis une couleur pour chaque classe différente

    open fun Reactionballe(b: Balle) {                                          // fonction qui gère le contact entre un bloc et une balle
        if (RectF.intersects(bloc, b.r)) {
            if(b.x ==x1 || b.x==x2 ||b.x+b.diametre==x1 ||b.x+b.diametre==x2){  // on évalue si la balle touche une paroie verticale
                b.changeDirection(false)
            }
            if(b.y ==y1 || b.y==y2 ||b.y+b.diametre==y1 ||b.y+b.diametre==y2){ // paroie horizontale
                b.changeDirection(true)
            }
        }
    }

    fun setRect(){
        bloc.set(x1, y1, x2, y2)
    }

    open fun draw(canvas: Canvas) {   // dessine un rectangle "bloc" à la position des paramètres (x1, y1) et (x2,y2)
        if (this.OnScreen){
            BlocPaint.setStyle(Paint.Style.FILL)
            BlocPaint.color = color
            canvas.drawRect(bloc, BlocPaint)
        }
        if (this.OnScreen){
            BlocPaint.setStyle(Paint.Style.STROKE)
            BlocPaint.setColor(Color.BLACK)
            canvas.drawRect(bloc, BlocPaint)
        }
    }
}
