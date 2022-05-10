package com.example.projet

import android.graphics.Color
import android.graphics.RectF
import java.util.*

class Bonus(x1: Float,y1 : Float, x2 : Float, y2:Float) : Effets(x1,y1, x2, y2){
    override var OnScreen = true
    override var color = Color.rgb(137, 85, 227)
    override var incrementation_de_taille_x = 50f
    override var incrementation_de_vitesse = 200

    }
