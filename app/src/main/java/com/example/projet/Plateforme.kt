package com.example.projet
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF

import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Plateforme (x1:Float, y1: Float, x2: Float, y2: Float, ): Blocs(x1, y1, x2, y2), Bouge {
    var v = 2.0F
    var dx : Int
    var color = Color.argb(255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256))

    init {
        if (random.nextDouble() > 0.5) dx = 1 else dx = -1
    }

    override fun reactionBalle(b: Balle) {
        TODO("Not yet implemented")
    }


    override fun bouge(canvas: Canvas?) {
        r.offset(v * dx)
        draw(canvas)
    }
}