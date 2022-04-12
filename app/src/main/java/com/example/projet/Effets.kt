package com.example.projet

import android.graphics.*
import java.util.*

abstract class Effets (x1 : Float, y1 : Float, val diametre: Float) {
    val r = RectF(x1 ,y1, x1 + diametre, y1 + diametre)
    val random = Random()
    val paint = Paint()
    var color = Color.argb(255, random.nextInt(256),
        random.nextInt(256), random.nextInt(256))
    abstract fun vitesseballe()
    abstract fun tailleplateforme()
    abstract fun draweffets()

}