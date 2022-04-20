package com.example.projet
import android.graphics.*

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.graphics.Color
import android.graphics.Canvas
import android.view.View

class Plateforme (x1:Float, y1: Float, x2: Float, y2: Float, ): Blocs(x1, y1, x2, y2){
    var v = 2.0F
    var dx : Int
    override val color = Color.RED


    init {
        if (random.nextDouble() > 0.5) dx = 1 else dx = -1
    }

    fun reactionBalle(b: Balle) {
        if (RectF.intersects(r, b.r)){
            b.changeDirection(true)
        }
    }




     fun refresh(destination: Point) {
         TODO()
    }
}