package com.example.projet

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.lang.Math.random
import kotlin.properties.Delegates
import kotlin.random.Random
import kotlin.random.nextInt

class DrawingView2 @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback,Runnable {
    lateinit var canvas: Canvas
    var keepdrawing = true
    val backgroundPaint = Paint()
    lateinit var thread: Thread
    var drawing: Boolean = true

    var largeur = 0f
    var hauteur = 0f

    lateinit var lesParois : Array<Parois2>
    lateinit var lesMonstres: Array<Monstre2>
    lateinit var lesCarres : Array<Carre2>
    lateinit var balle : ArrayList<Balle2>
    lateinit var plateforme : ArrayList<Plateforme2>

    init {
        backgroundPaint.color = Color.TRANSPARENT
    }



    override fun onSizeChanged(w: Int,h: Int,oldw: Int,oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        hauteur = h.toFloat()
        largeur = w.toFloat()

        plateforme = arrayListOf(Plateforme2(w/3f,h*7/8f, w-w/3f, h* 7/8f - w/50))

        balle = arrayListOf(Balle2( w * 1/2f -50f , h* 2/3f - 50f , 100f))

        lesMonstres = arrayOf(
            Monstre2((Random.nextInt(w/50 + 100, 1*w -100).toFloat()),(Random.nextInt(w/50 +100, 1*(h*1/2)).toFloat()),80f),
            Monstre2((Random.nextInt(w/50 + 100 , 1*w -100).toFloat() - 80 ),(Random.nextInt(w/50 +100, 1*(h*1/2)).toFloat()),80f),
            Monstre2((Random.nextInt(w/50 + 100, 1*w -100).toFloat() - 80),(Random.nextInt(w/50 +100, 1*(h*1/2)).toFloat()),80f)
        )

        lesParois = arrayOf(
            Parois2(0f, w/50f, largeur, 0f), //haut
            Parois2(0f, hauteur, w/50f, 0f), // gauche
            /* Parois2(0f, hauteur, largeur, hauteur - w/50f), */ //bas , potentiellement changé  ?
            Parois2(largeur - w/50f, hauteur, largeur , 0f)) //droite

        lesCarres = arrayOf(
            Carre2(w/50f + 100f, w/50f + 200f, w/50f + 200f,w/50f + 100f,1),
            Carre2(w/50f + 210f, w/50f + 200f, w/50f + 310f,w/50f + 100f,1),
            Carre2(w/50f + 320f, w/50f + 200f, w/50f + 420f,w/50f + 100f,1),

            Carre2(largeur - w/50f -200f, w/50f + 200f, largeur - w/50f - 100f,w/50f + 100f,1),
            Carre2(largeur - w/50f -310f, w/50f + 200f, largeur - w/50f -210f,w/50f + 100f,1),
            Carre2(largeur - w/50f -420f, w/50f + 200f, largeur - w/50f -320f,w/50f + 100f,1),

            Carre2(w/50f + 100f, w/50f + 600f, w/50f + 200f,w/50f + 500f,1),
            Carre2(w/50f + 210f, w/50f + 600f, w/50f + 310f,w/50f + 500f,1),
            Carre2(w/50f + 320f, w/50f + 600f, w/50f + 420f,w/50f + 500f,1),

            Carre2(largeur - w/50f -200f, w/50f + 600f, largeur - w/50f - 100f,w/50f + 500f,1),
            Carre2(largeur - w/50f -310f, w/50f + 600f, largeur - w/50f -210f,w/50f + 500f,1),
            Carre2(largeur - w/50f -420f, w/50f + 600f, largeur - w/50f -320f,w/50f + 500f,1),

        )

    }

    fun pause() {
        drawing = false
        thread.join()
    }

    fun resume() {
        drawing = true
        thread = Thread(this)
        thread.start()
    }

    fun draw() {
        if (holder.surface.isValid) {
            canvas = holder.lockCanvas()
            canvas.drawRect(0F, 0F, canvas.getWidth()*1F,
                canvas.getHeight()*1F, backgroundPaint)

            for (parois in lesParois){
                parois.draw(canvas)
            }

            for (monstres in lesMonstres){
                //monstres.verifcontactmutuelle(lesMonstres)
                        //monstres.verifcontactbloc(monstres)

                monstres.draw(canvas)
            }

            for (balle in balle){
                balle.draw(canvas)
            }
            for (plat in plateforme){
                plat.draw(canvas)
            }

            for (carre in lesCarres){
                carre.draw(canvas)
            }


            holder.unlockCanvasAndPost(canvas)
        }
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                val x = e.rawX.toInt() - 100
                val y = e.rawY.toInt() - 500
            }
        }
        return true
    }

    override fun run() {
        while(drawing)
            draw()
    }



    override fun surfaceChanged(
        holder: SurfaceHolder, format: Int,
        width: Int, height: Int) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        thread = Thread(this)
        thread.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        thread.join()
    }
}