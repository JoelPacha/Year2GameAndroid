package com.example.projet
import android.graphics.*

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.graphics.Color
import android.view.MotionEvent
import android.view.View

class Vide(x1:Float, y1:Float, x2:Float, y2:Float): Blocs2(x1, y1, x2, y2) {
    override val color = Color.RED


    fun Reactionballe(b: Balle2) {
        if (RectF.intersects(b.r, this.bloc)){
            b.disparait()
            b.reset()
        }
    }

}