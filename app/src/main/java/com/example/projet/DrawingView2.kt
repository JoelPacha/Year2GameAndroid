package com.example.projet

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
    val paint = Paint()
    lateinit var thread: Thread
    var keepdrawing: Boolean = true
    val Jungle = BitmapFactory.decodeResource(resources, R.drawable.backgroundjungle)
    var largeur = 0f
    var hauteur = 0f


    var lesParois = arrayListOf(Parois2(0f,0f,0f,0f))
    var lesMonstres =  arrayOf(Monstre2(0f,0f,0f) )
    var lesCarres = arrayOf(Carre2(0f,0f,0f,0f,0))

    var balle = arrayOf(Balle2(0f,0f,0f))
    var plateforme = arrayOf(Plateforme2(0f,0f,0f,0f))
    var vide = arrayOf(Vide(0f,0f,0f,0f))



    override fun onSizeChanged(w: Int,h: Int,oldw: Int,oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        hauteur = h.toFloat()
        largeur = w.toFloat()

        plateforme = arrayOf(
            Plateforme2(w/3f,h*7/8f, w-w/3f, h* 7/8f - w/50)
        )
        balle = arrayOf(
            Balle2( w * 1/2f -50f , h* 2/3f - 50f , 100f)
        )



        vide = arrayOf(
            Vide(0f,hauteur-150,largeur,hauteur-w/50f)
        )

        lesMonstres = arrayOf(
            Monstre2((Random.nextInt(w/50 + 100, 1*w -100).toFloat()),(Random.nextInt(w/50 +100, 1*(h*1/2)).toFloat()),80f),
            Monstre2((Random.nextInt(w/50 + 100 , 1*w -100).toFloat() - 80 ),(Random.nextInt(w/50 +100, 1*(h*1/2)).toFloat()),80f),
            Monstre2((Random.nextInt(w/50 + 100, 1*w -100).toFloat() - 80),(Random.nextInt(w/50 +100, 1*(h*1/2)).toFloat()),80f)
        )

        lesParois = arrayListOf(
            Parois2(0f, 0f, w/50f, 2000f), // gauche
            Parois2(0f, w/50f, largeur, 0f), //haut
            Parois2(largeur - w/50f, 0f, largeur, 2000f)) //droite

        lesCarres = arrayOf(
            Carre2(w/50f + 100f, w/50f + 200f, w/50f + 170f,w/50f + 130f,1),
            Carre2(w/50f + 210f, w/50f + 200f, w/50f + 280f,w/50f + 130f,1),
            Carre2(w/50f + 320f, w/50f + 200f, w/50f + 390f,w/50f + 130f,1),

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
            canvas = holder.lockCanvas()
            canvas.drawBitmap(Jungle,null,Rect(0, 0, canvas.getWidth(),canvas.getHeight()),paint)
            for (parois in lesParois){
                parois.draw(canvas)
            }

            for (monstres in lesMonstres){
                if (monstres.verifcontactbloc(lesCarres)){
                    monstres.draw(canvas)
                }
                else {
                    val nouveaux_monstres = Monstre2((Random.nextInt(largeur.toInt()/50 + 100, 1*largeur.toInt() -100).toFloat()),(Random.nextInt(largeur.toInt()/50 +100, 1*(hauteur.toInt()*1/2)).toFloat()),80f)
                    nouveaux_monstres.draw(canvas)
                }
            }

            for (carre in lesCarres){
                carre.draw(canvas)

            }

            for (b in balle){
                b.draw(canvas)
            }

            for (plat in plateforme){
                plat.draw(canvas)
            }

            for (v in vide){
                v.draw(canvas)
            }


            holder.unlockCanvasAndPost(canvas) //Liberation du canvas
        }
    }

    /*
    override fun onTouchEvent(e: MotionEvent): Boolean {
        balle[0].changeDirection(true)
        return true
    }

     */

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
        balle[0].bouge(interval) // fait bouger la balle
        plateforme[0].Reactionballe(balle[0])


        for (monstres in lesMonstres){
            monstres.bouge(interval)
        }

        for (parois in lesParois){
            parois.Reactionballe(balle[0])
            for (monstres in lesMonstres){
                parois.Reactionballe(monstres)
            }

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