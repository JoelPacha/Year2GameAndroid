package com.example.projet


import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import java.util.*


class DrawingView @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback, Runnable {
    val backgroundPaint = Paint()
    val random = Random()
    val balle = Balle(random.nextFloat()*500, random.nextFloat()*1000,random.nextFloat()*500, this)
    lateinit var canvas: Canvas
    lateinit var thread: Thread
    var keepdrawing = true
    val plateforme = Plateforme(0f, 0f, 0f, 0f)
    var screenwidth = Resources.getSystem().getDisplayMetrics().widthPixels.toFloat()
    var screenheight = Resources.getSystem().getDisplayMetrics().heightPixels.toFloat()
    val canvasH = screenheight - 500.toFloat()
    val canvasW = screenwidth - 25.toFloat()
    var LesCarres = arrayOf(Carre(screenheight/2, screenwidth/ 2, screenheight-10, screenwidth+10,3))
    var LesParois = arrayOf(Parois(5f, 5f, 25f, canvasH), Parois(5f, 5f, canvasW, 25f),
    Parois(5f, canvasH, canvasW, canvasH+25f), Parois(canvasW, 5f, canvasW + 25, canvasH + 25))
    var LesMonstres = arrayOf(Monstre(random.nextFloat()*500, random.nextFloat()*1000,random.nextFloat()*500, this),
    Monstre(random.nextFloat()*500, random.nextFloat()*1000,random.nextFloat()*500, this)
    )

    init {
        backgroundPaint.color = Color.TRANSPARENT

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)



        TODO("modifier tous les attributs des objets à l'écran pour les afficher en faisant par essais erreur")

    }
    fun draw() {
        canvas = holder.lockCanvas()
        canvas.drawRect(0f, 0f, canvas.width.toFloat(),
            canvas.height.toFloat(), backgroundPaint)
        backgroundPaint.color = Color.WHITE
        balle.draw(canvas)
        plateforme.draw(canvas)
        for (carre in LesCarres){
            carre.draw(canvas)
        }
        for(paroie in LesParois){
            paroie.draw(canvas)
        }
        for (monstre in LesMonstres){
            monstre.draw(canvas)
        }
    }

    override fun onTouchEvent(e: MotionEvent): Boolean { //
        val action = e.action
        if( action ==MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_MOVE ) {
            plateforme.bouge(e)
        }
        return true
    }

    fun pause(){
        keepdrawing = false
        thread.join()


    }
    fun resume(){
        keepdrawing = true
        thread = Thread(this)
        thread.start()
    }

    fun refreshAll(FrameTime: Float){     // A chaque frame, la fonction rafraîchit tout le DrawingView et assigne les nouvelles positions aux Ovnis
        balle.bouge(FrameTime) // fait bouger la balle et les monstres

        for (monstre in LesMonstres){
            monstre.bouge(FrameTime)
            monstre.mangerBalle(balle)
        }
        for (carre in LesCarres){ //vérifie si les carrés et les parois rentre en contact avec la balle à chaque frame
            carre.Reactionballe(balle)
        }
        for (paroie in LesParois){
            paroie.Reactionballe(balle)
        }

    }

    override fun run() {
        var OldFrame = System.currentTimeMillis()
        while(keepdrawing){
            val NewFrame = System.currentTimeMillis()
            val FrameTime = (NewFrame - OldFrame).toFloat()
            refreshAll(FrameTime/1000)          // on calcule le temps que prends le temps qui s'écoule entre deux frame que dessine le DrawingView
            draw()                                       // fonction inspirée de celle du jeu canon
            OldFrame = NewFrame
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


}

