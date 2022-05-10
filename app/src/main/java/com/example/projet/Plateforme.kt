package com.example.projet
import android.graphics.*

import android.graphics.Color
import android.view.MotionEvent

class Plateforme(x1:Float, y1:Float, x2:Float, y2:Float): Blocs(x1, y1, x2, y2) {
    override var color = Color.rgb(250, 170, 251)
    var Dx = 0f
    var Dy = 0f
    var xg = bloc.left
    var xd = bloc.right  // on utilise xg et xd pour éviter d'avoir des problèmes quand la plateforme change de taille
    var ytop = bloc.top
    var ybottom = bloc.bottom

    fun bouge(e: MotionEvent,h: Float,  w: Float ) {      // fonction qui permet de bouger la plateforme en maintenant appuyé et glissant le doigt sur l'écran

        val action = e.action                   // sorte d'action: un click ou un glissement
        when(action){
            MotionEvent.ACTION_DOWN -> {        //repère le moment où le doigt touche l'écran
                Dx = e.rawX - this.xg           // Dx la distance entre le click et le côté gauche de la plateforme
                Dy = e.rawY - this.ytop
                this.set(xg, ytop, xd, ybottom)
            }
            MotionEvent.ACTION_MOVE -> {        // Repère le moment où on glisse
                xg = e.rawX - Dx                // Modifie la position de la plateforme en la glissant vers la gauche ou la droite
                xd = xg + this.largeur
                ytop = e.rawY - Dy
                ybottom = ytop + this.longueur
                this.bloquerPlateforme(h, w)
                }
            }
        }

    fun bloquerPlateforme(h: Float,  w: Float){  // fonction qui confine la paroies dans la map et l'empêche de dépasser les paroies ou le transparent en allant trop haut
        if (xg > w/50f+w/100f) {
            this.set(xg, ytop, xd, ybottom)
        }
        else if (xg<w/50f+w/100f){
            xg = w/50f+w/100f
            xd = xg + largeur
            this.set(xg, ytop, xd, ybottom)
        }
        if (xd <w- w/50f-w/100f){
            this.set(xg,ytop,xd,ybottom)
        }
        else if(xd>w-w/50f-w/100){
            xd = w-w/50f-w/100f
            xg = xd-largeur
            this.set(xg, ytop, xd, ybottom)
        }
        if (ytop >=h/2f +h/461.8f){
            this.set(xg,ytop,xd,ybottom)
        }

        else if(ytop<h/2f +h/461.8f){
            ytop = h/2f +h/461.8f
            ybottom = ytop +longueur
            this.set(xg, ytop, xd, ybottom)
        }
        if (ybottom <h-w/50f-w/100){
            this.set(xg,ytop,xd,ybottom)
        }
        else if(ybottom >=h-w/50f-w/100){
            ybottom = h-w/50f-w/100
            ytop = ybottom-longueur
            this.set(xg, ytop, xd, ybottom)
        }
    }


    fun Reactionballe(b: Balle, h: Float, w: Float) {   // reaction entre la balle et la plateforme: dépend de la position de celle-ci
        if (ytop >= h * 7 / 8f - w / 10) {
            if (RectF.intersects(b.r, this.bloc)) {
                b.stopBalle(this)
                b.color = Color.rgb(224, 74, 224)
            }
        }
        else{
            if (RectF.intersects(b.r, this.bloc)) {
                b.directionAbsolue(0)

            }
        }
    }


    fun set(x1:Float,y1:Float,x2:Float,y2:Float){  // redéfinit le bloc de la plateforme
        bloc.set(x1,y1,x2,y2)
    }


}