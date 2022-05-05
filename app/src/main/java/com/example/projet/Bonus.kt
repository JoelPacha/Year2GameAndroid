package com.example.projet

import android.graphics.Color
import android.graphics.RectF
import java.util.*

class Bonus(x:Float,y:Float,diametre:Float) : Effets(x,y,diametre){
    override var OnScreen = true
    override val color = Color.YELLOW
    override var incrementation_de_taille_x = 50f
    override var incrementation_de_vitesse = -200


    }
