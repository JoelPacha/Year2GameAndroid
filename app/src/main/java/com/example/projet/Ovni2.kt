package com.example.projet

import android.graphics.*
import java.lang.Math.abs
import java.util.*

abstract class Ovni2(var x:Float, var y: Float, var diametre : Float) {

    var r = RectF(x, y, x + diametre, y + diametre)
    val random = Random()
    abstract var dx: Float
    abstract var dy: Float
    abstract var VitesseOvni: Float
    //var OnScreen = true // booléen qui vérifie si l'élément est à l'écran ou est détruit ( pour balle, carré, fantôme etc)
    val paint = Paint()
    abstract val color: Int
    var distance_frame = 0f


    open fun draw(canvas: Canvas?) {
        paint.setStyle(Paint.Style.FILL)
        paint.color = color

        canvas?.drawOval(r, paint)
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK)
        canvas?.drawOval(r, paint)

    }

    fun changeDirection(direction: Boolean) { // la fonction change la vitesse vertical si direction ==true
        if (direction) {
            this.dy = -dy
        }
        else{
            this.dx = -dx
        }
        r.offset(1.0f*dx, 1.0f*dy)
    }



    fun directionAbsolue(f: Int) {            // évite d'avoir la balle qui oscille dans les paroies ou plateformes
        if (f==0) {
            dy = -abs(dy)
        }
        else if (f==1){
            dx = -abs(dx)
        }
        else if (f==2){
            dx = abs(dx)
        }
        else if (f==3){
            dy = abs(dy)
        }
        r.offset(1f * dx, 1f * dy)
    }




    open fun bouge(FrameTime: Double){
        distance_frame = (FrameTime * VitesseOvni).toFloat()
        x += dx*distance_frame                                //  permet d'actualiser les positions de la balle en temps réel pour les utiliser si besoin
        y += dy*distance_frame
        r.offset(dx*distance_frame, dy*distance_frame) // Les dx et dy ne sont pas la vitesse, il servent juste à annuler ou inverser le signe des déplacement "distance_frame"

    }






}