package com.example.projet
import android.graphics.Color

//import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class Balle2( x:Float, y: Float, diametre: Float): Ovni2(x, y, diametre)   {

    override var dx = -1f
    override var dy = -1f
    override var VitesseOvni =100f
    override val color = Color.BLUE







}