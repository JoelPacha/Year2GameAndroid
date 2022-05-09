package com.example.projet

import android.content.Context
import android.graphics.BitmapFactory
import android.util.AttributeSet
import java.util.jar.Attributes
import kotlin.random.Random

class View3(context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0) : DrawingView(context, attributes, defStyleAttr) {


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        Jungle =  BitmapFactory.decodeResource(resources, R.drawable.niveau3)

        lesMonstres.addAll(listOf(Monstre((Random.nextInt(w/50, (w - w/50 - h/28.86).toInt()).toFloat() - h/28.86f),(Random.nextInt(marge.toInt() + w/50, 1*(h/2-h/28.86.toInt())).toFloat()),diametre),
        Monstre((Random.nextInt(w/50, (w - w/50 - h/28.86).toInt()).toFloat() - h/28.86f),(Random.nextInt(marge.toInt() + w/50, 1*(h/2-h/28.86.toInt())).toFloat()),diametre)))

        lesCarres.addAll(listOf())
        lesBonus.addAll(listOf())
        lesMalus.addAll(listOf())
    }

}