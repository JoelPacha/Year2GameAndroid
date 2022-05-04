package com.example.projet

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import kotlin.random.Random

class DrawingView2 @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback,Runnable {
    lateinit var canvas: Canvas
    val viePaint = Paint()
    lateinit var thread: Thread
    var keepdrawing: Boolean = true
    val Jungle = BitmapFactory.decodeResource(resources, R.drawable.backgroundjungle)
    val life = BitmapFactory.decodeResource(resources,R.drawable.life)
    var vie = 3
    var largeur = 0f
    var hauteur = 0f
    var param = 0f

    var lesParois = arrayOf(Parois2(0f,0f,0f,0f))
    var lesMonstres =  arrayListOf(Monstre2(0f,0f,0f) )
    var lesCarres = arrayListOf(Carre2(0f,0f,0f,0f,0))
    var lesBonus = arrayListOf(Malus(0f,0f,0f))
    var lesMalus = arrayListOf(Malus(0f,0f,0f))
    var balle = Balle2(0f,0f,0f)
    var plateforme = Plateforme2(0f,0f,0f,0f)
    var vide = Vide(0f,0f,0f,0f)
    var nb_vie = Vie(0f,0f,0f)



    override fun onSizeChanged(w: Int,h: Int,oldw: Int,oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        hauteur = h.toFloat()
        largeur = w.toFloat()
        param = (largeur - 2*w/47  )/ 10f


        plateforme = Plateforme2(w/3f,h*7/8f - w/50, w-w/3f, h* 7/8f)
        balle = Balle2( w * 1/2f -50f , h* 2/3f - 50f , 100f)
        vide = Vide(0f,hauteur-w/50f,largeur,hauteur)



        lesMonstres = arrayListOf(
            Monstre2((Random.nextInt(w/50 + 100, 1*w -100).toFloat()),(Random.nextInt(w/50 +100, 1*(h*1/2)).toFloat()),80f),
            Monstre2((Random.nextInt(w/50 + 100 , 1*w -100).toFloat() - 80 ),(Random.nextInt(w/50 +100, 1*(h*1/2)).toFloat()),80f),
            Monstre2((Random.nextInt(w/50 + 100, 1*w -100).toFloat() - 80),(Random.nextInt(w/50 +100, 1*(h*1/2)).toFloat()),80f)
        )

        lesParois = arrayOf(
            Parois2(0f, 150f, w/50f, hauteur), // gauche
            Parois2(0f, 150f, largeur, w/50f+150f), //haut
            Parois2(largeur - w/50f, 150f, largeur, hauteur)) //droite


        lesCarres = arrayListOf(

            /*Carre2(w/47f , w/47f, w/47f+ param ,w/47f+ param,1),
            Carre2(w/47f + param, w/47f, w/47f+ 2*param,w/47f + param,1),
            Carre2(w/47f + 2*param , w/47f , w/47f + 3*param,w/47f + param,1),
            Carre2(w/47f + 3*param , w/47f, w/47f + 4*param ,w/47f + param,1),
            Carre2(w/47f + 4*param, w/47f, w/47f + 5*param,w/47f + param,1),
            Carre2(w/47f + 5*param , w/47f, w/47f + 6*param,w/47f +param,1),
            Carre2(w/47f + 6*param , w/47f , w/47f + 7*param ,w/47f + param,1),
            Carre2(w/47f + 7*param, w/47f, w/47f + 8*param,w/47f + param,1),
            Carre2(w/47f + 8*param , w/47f, w/47f + 9*param,w/47f + param,1),
            Carre2(w/47f + 9*param , w/47f, w/47f + 10*param ,w/47f + param,1),*/


            /*Carre2(w/47f , w/47f+param, w/47f+ param ,w/47f+ 2*param,1),
            Carre2(w/47f + param, w/47f+param, w/47f+ 2*param,w/47f+ 2*param,1),
            Carre2(w/47f + 2*param , w/47f+param , w/47f + 3*param,w/47f+ 2*param,1),
            Carre2(w/47f + 3*param , w/47f+param, w/47f + 4*param ,w/47f+ 2*param,1),
            Carre2(w/47f + 4*param, w/47f+param, w/47f + 5*param,w/47f+ 2*param,1),
            Carre2(w/47f + 5*param , w/47f+param, w/47f + 6*param,w/47f+ 2*param,1),
            Carre2(w/47f + 6*param , w/47f+param , w/47f + 7*param ,w/47f+ 2*param,1),
            Carre2(w/47f + 7*param, w/47f+param, w/47f + 8*param,w/47f+ 2*param,1),
            Carre2(w/47f + 8*param , w/47f+param, w/47f + 9*param,w/47f+ 2*param,1),
            Carre2(w/47f + 9*param , w/47f+param, w/47f + 10*param ,w/47f+ 2*param,1),
*/

            /*Carre2(w/47f , w/47f+2*param, w/47f+ param ,w/47f+ 3*param,1),
            Carre2(w/47f + param, w/47f+2*param, w/47f+ 2*param,w/47f+ 3*param,1),
            Carre2(w/47f + 2*param , w/47f+2*param , w/47f + 3*param,w/47f+ 3*param,1),
            Carre2(w/47f + 3*param , w/47f+2*param, w/47f + 4*param ,w/47f+ 3*param,1),
            Carre2(w/47f + 4*param, w/47f+2*param, w/47f + 5*param,w/47f+ 3*param,1),
            Carre2(w/47f + 5*param , w/47f+2*param, w/47f + 6*param,w/47f+ 3*param,1),
            Carre2(w/47f + 6*param , w/47f+2*param , w/47f + 7*param ,w/47f+ 3*param,1),
            Carre2(w/47f + 7*param, w/47f+2*param, w/47f + 8*param,w/47f+ 3*param,1),
            Carre2(w/47f + 8*param ,w/47f+2*param, w/47f + 9*param,w/47f+ 3*param,1),
            Carre2(w/47f + 9*param , w/47f+2*param, w/47f + 10*param ,w/47f+ 3*param,1),
*/
            /*Carre2(w/47f , w/47f+3*param, w/47f+ param ,w/47f+ 4*param,1),
            Carre2(w/47f + param, w/47f+3*param, w/47f+ 2*param,w/47f+ 4*param,1),
            Carre2(w/47f + 2*param , w/47f+3*param , w/47f + 3*param,w/47f+ 4*param,1),
            Carre2(w/47f + 3*param , w/47f+3*param, w/47f + 4*param ,w/47f+ 4*param,1),
            Carre2(w/47f + 4*param, w/47f+3*param, w/47f + 5*param,w/47f+ 4*param,1),
            Carre2(w/47f + 5*param , w/47f+3*param, w/47f + 6*param,w/47f+ 4*param,1),
            Carre2(w/47f + 6*param , w/47f+3*param , w/47f + 7*param ,w/47f+ 4*param,1),
            Carre2(w/47f + 7*param, w/47f+3*param, w/47f + 8*param,w/47f+ 4*param,1),
            Carre2(w/47f + 8*param ,w/47f+3*param, w/47f + 9*param,w/47f+ 4*param,1),
            Carre2(w/47f + 9*param , w/47f+3*param, w/47f + 10*param ,w/47f+ 4*param,1),
*/

            /*Carre2(w/47f , w/47f+4*param, w/47f+ param ,w/47f+ 5*param,1),
            Carre2(w/47f + param, w/47f+4*param, w/47f+ 2*param,w/47f+ 5*param,1),
            Carre2(w/47f + 2*param , w/47f+4*param , w/47f + 3*param,w/47f+ 5*param,1),
            Carre2(w/47f + 3*param , w/47f+4*param, w/47f + 4*param ,w/47f+ 5*param,1),
            Carre2(w/47f + 4*param, w/47f+4*param, w/47f + 5*param,w/47f+ 5*param,1),
            Carre2(w/47f + 5*param , w/47f+4*param, w/47f + 6*param,w/47f+ 5*param,1),
            Carre2(w/47f + 6*param , w/47f+4*param , w/47f + 7*param ,w/47f+ 5*param,1),
            Carre2(w/47f + 7*param, w/47f+4*param, w/47f + 8*param,w/47f+ 5*param,1),
            Carre2(w/47f + 8*param ,w/47f+4*param, w/47f + 9*param,w/47f+ 5*param,1),
            Carre2(w/47f + 9*param , w/47f+4*param, w/47f + 10*param ,w/47f+ 5*param,1),
*/


            /*Carre2(w/47f , w/47f+5*param, w/47f+ param ,w/47f+ 6*param,1),
            Carre2(w/47f + param, w/47f+5*param, w/47f+ 2*param,w/47f+ 6*param,1),
            Carre2(w/47f + 2*param , w/47f+5*param , w/47f + 3*param,w/47f+ 6*param,1),
            Carre2(w/47f + 3*param , w/47f+5*param, w/47f + 4*param ,w/47f+ 6*param,1),
            Carre2(w/47f + 4*param, w/47f+5*param, w/47f + 5*param,w/47f+ 6*param,1),
            Carre2(w/47f + 5*param , w/47f+5*param, w/47f + 6*param,w/47f+ 6*param,1),
            Carre2(w/47f + 6*param , w/47f+5*param , w/47f + 7*param ,w/47f+ 6*param,1),
            Carre2(w/47f + 7*param, w/47f+5*param, w/47f + 8*param,w/47f+ 6*param,1),
            Carre2(w/47f + 8*param ,w/47f+5*param, w/47f + 9*param,w/47f+ 6*param,1),
            Carre2(w/47f + 9*param , w/47f+5*param, w/47f + 10*param ,w/47f+ 6*param,1),
*/

            )

    }

    fun pause() {
        keepdrawing = false
        thread.join()
    }

    fun resume() {
        keepdrawing = true
        thread = Thread(this)
        thread.start()
    }

    fun draw() {
        if (holder.surface.isValid) {
            viePaint.setStyle(Paint.Style.FILL)
            viePaint.setColor(Color.WHITE)
            viePaint.setTextSize(110F)
            canvas = holder.lockCanvas()
            canvas.drawBitmap(Jungle,null,Rect(0, 0, canvas.getWidth(),canvas.getHeight()),null)
            canvas.drawBitmap(life,null,Rect(125,25,225,117),null)
            canvas.drawText("0",50f,110f,viePaint)
            for (parois in lesParois){
                parois.draw(canvas)
            }
            for (carre in lesCarres){
                carre.draw(canvas)

            }

            for (monstres in lesMonstres){
                monstres.draw(canvas)

            }

            balle.draw(canvas)
            plateforme.draw(canvas)
            vide.draw(canvas)


            holder.unlockCanvasAndPost(canvas) //Liberation du canvas
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(e: MotionEvent): Boolean {
        /*if (e.action == MotionEvent.ACTION_DOWN || e.action == MotionEvent.ACTION_MOVE){
            println("appuyé!")
            plateforme.bouge(e)
        }*/
        plateforme.bouge(e)
        return true
    }



    override fun run() {
        var OldFrame = System.currentTimeMillis()
        while(keepdrawing){
            val NewFrame = System.currentTimeMillis()
            val FrameTime = (NewFrame- OldFrame).toDouble()
            refreshAll(FrameTime)          // on calcule le temps que prends le temps qui s'écoule entre deux frame que dessine le DrawingView
            draw()                                       // fonction inspirée de celle du jeu canon
            OldFrame = NewFrame
            }
        }



    fun refreshAll(FrameTime: Double){
        val interval = FrameTime/1000 // A chaque frame, la fonction rafraîchit tout le DrawingView et assigne les nouvelles positions aux Ovnis
        balle.bouge(interval) // fait bouger la balle
        plateforme.Reactionballe(balle)
        vide.Reactionballe(balle)


        for (monstres in lesMonstres){
            monstres.bouge(interval)
            monstres.reaction(lesParois)
            monstres.mangerBalle(balle,nb_vie)
            plateforme.Reactionballe(monstres)
            vide.Reactionballe(monstres)
        }

        for (parois in lesParois){
            parois.Reactionballe(balle)
        }

        for (carre in lesCarres){
            carre.Reactionballe(balle)
        }

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