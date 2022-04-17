package com.example.projet


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

import java.util.*
import kotlin.collections.ArrayList


class DrawingView @JvmOverloads
constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback, Runnable {
    val backgroundPaint = Paint()
    val random = Random()
    val balle = Balle(random.nextFloat()*500, random.nextFloat()*1000,random.nextFloat()*500)
    lateinit var LesParois: Array<Parois>
    lateinit var LesBalles: Array<Balle>
    lateinit var canvas: Canvas
    lateinit var thread: Thread
    var drawing = true

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val canvasH = h - 500.toFloat()
        val canvasW = w - 25.toFloat()
        LesParois = arrayOf(
            Parois(5f, 5f, 25f, canvasH),
            Parois(5f, 5f, canvasW, 25f),
            Parois(5f, canvasH, canvasW, canvasH + 25f),
            Parois(canvasW, 5f, canvasW + 25, canvasH + 25)
        )
    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        backgroundPaint.color = Color.WHITE
        canvas?.drawRect(0F,0F,width.toFloat(),height.toFloat(),backgroundPaint)
        balle.draw(canvas)
    }




    override fun surfaceCreated(p0: SurfaceHolder) {
        TODO("Not yet implemented")
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        TODO("Not yet implemented")
    }

    override fun run() {
        while (drawing) {
            for (b in lesBalles) {
                if (!b.dead) {
                    b.bouge(LesParois,LesBalles)
                }
            }
            onDraw()
        }
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = e.rawX.toInt() - 100
                val y = e.rawY.toInt() - 300
                LesBalles.add(Balle(x.toFloat(), y.toFloat(), 30f))
            }
        }
        return true
    }
    fun pause(){
        drawing = false
        thread.join()

    }
    fun resume(){
        drawing = true
        thread = Thread(this)
        thread.start()
    }
    override fun run() {
        while(drawing){
            for (b in LesBalles){
                b.bouge(LesParois, LesBalles)
            }
            onDraw()
        }
    }





}

