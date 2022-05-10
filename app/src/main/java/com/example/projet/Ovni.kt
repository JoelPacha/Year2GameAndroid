package com.example.projet

import android.graphics.*
import java.lang.Math.abs
import java.util.*

abstract class Ovni(var x:Float, var y: Float, var diametre : Float) {

    var r = RectF(x, y, x +diametre, y + diametre)  //définit le carré autour de chaque balle
    val random = Random()  //valeur aléatoire
    abstract var dx: Float  //chaque classe aura un coefficient différent car est inité de manière différente (attribut) (ce ne sont pas les vitesse à proprement dite mais juste des coefficient)
    abstract var dy: Float
    abstract var VitesseOvni: Float  //chaque classe aura une vitesse différente (attribut)
    val paint = Paint()
    abstract val color: Int  //chaque classe aura une couleur différente (attribut)
    var distance_frame = 0f  //distance entre chaque frame
    var posx = x
    var posy = y


    fun draw(canvas: Canvas?) {  //fonction qui dessinera l'objet de la classe
        paint.setStyle(Paint.Style.FILL) //set l'intérieur
        paint.color = color
        canvas?.drawOval(r, paint) //dessine un ovale plein
        paint.setStyle(Paint.Style.STROKE);  //set le contour
        paint.setColor(Color.BLACK)
        canvas?.drawOval(r, paint)//dessine le contour

    }

    fun changeDirection(direction: Boolean) { // fonction qui change la direction de l'objet grâce au coefficient de direction
        if (direction) {
            this.dy = -dy
        }
        else{
            this.dx = -dx
        }
        r.offset(1.0f*dx, 1.0f*dy)
    }


    fun directionAbsolue(f: Int) {  // évite d'avoir la balle qui oscille dans les paroies ou plateformes
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



    fun bouge(FrameTime: Double){
        distance_frame = (FrameTime * VitesseOvni).toFloat()
        posx =r.left  //  permet d'actualiser les positions de la balle en temps réel pour les utiliser si besoin
        posy = r.top
        r.offset(dx*distance_frame, dy*distance_frame) // Les dx et dy ne sont pas la vitesse, il servent juste à annuler ou inverser le signe des déplacement "distance_frame"

    }

    fun rand() : Float{
        var value = 0f
        if (random.nextDouble() > 0.5) value = 1f else value = -1f

        return value
    }


}