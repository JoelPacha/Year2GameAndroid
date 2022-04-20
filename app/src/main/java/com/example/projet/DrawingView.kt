package com.example.projet


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.*
import kotlin.collections.ArrayList


class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback, Runnable {
    val backgroundPaint = Paint()
    val random = Random()
    val balle = Balle(random.nextFloat()*500, random.nextFloat()*1000,random.nextFloat()*500)
    var screenheight = 0
    var screenwidth = 0
    lateinit var canvas: Canvas
    lateinit var thread: Thread
    var drawing = true
    val plateforme = Plateforme(0f, 0f, 0f, 0f)


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val canvasH = h - 500.toFloat()
        val canvasW = w - 25.toFloat()


    }
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        backgroundPaint.color = Color.WHITE
        canvas?.drawRect(0F,0F,width.toFloat(),height.toFloat(),backgroundPaint)
        balle.draw(canvas)
        for (p in LesParois) {
            p.draw(canvas)
        }
        for (c in LesCarres) {
            c.draw(canvas)
        }
    }




    override fun surfaceCreated(p0: SurfaceHolder) {
        thread = Thread(this)
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        thread.join()
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(e: MotionEvent): Boolean { //
        val action = e.action
        if (action == MotionEvent.ACTION_DOWN||action == MotionEvent.ACTION_MOVE
            ) {
            plateformeDrag(e)


        }
        return true
    }

    fun plateformeDrag(event: MotionEvent){
        val touchPoint = Point(event.x.toInt(), event.y.toInt())
        plateforme.refresh(touchPoint)
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
        TODO("Not yet implemented")
    }

}

