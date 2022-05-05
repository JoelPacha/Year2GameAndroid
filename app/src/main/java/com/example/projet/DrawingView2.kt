package com.example.projet

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.BitmapFactory
import android.graphics.*
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import kotlin.random.Random

class DrawingView2 @JvmOverloads constructor (context: Context, attributes: AttributeSet? = null, defStyleAttr: Int = 0): SurfaceView(context, attributes,defStyleAttr), SurfaceHolder.Callback,Runnable {
    lateinit var canvas: Canvas
    val viePaint = Paint()
    lateinit var thread: Thread
    var keepdrawing: Boolean = true
    val Jungle = BitmapFactory.decodeResource(resources, R.drawable.backgroundjungle)
    val life = BitmapFactory.decodeResource(resources,R.drawable.life)
    var largeur = 0f
    var hauteur = 0f
    var param = 0f
    var gameOver = false
    val activity = context as FragmentActivity

    var lesParois = arrayOf(Parois2(0f,0f,0f,0f))
    var lesMonstres =  arrayListOf(Monstre2(0f,0f,0f) )
    var lesCarres = arrayListOf(Carre2(0f,0f,0f,0f,0))
    var lesBonus = arrayListOf(Bonus(0f,0f,0f))
    var lesMalus = arrayListOf(Malus(0f,0f,0f))
    var balle = Balle2(0f,0f,0f,0)
    var plateforme = Plateforme2(0f,0f,0f,0f)
    var vide = Vide(0f,0f,0f,0f)
    var transparent = Transparent(0f,0f,0f,0f)



    override fun onSizeChanged(w: Int,h: Int,oldw: Int,oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        hauteur = h.toFloat()
        largeur = w.toFloat()
        val marge = 150f
        param = (largeur - 2*w/47  )/ 10f


        plateforme = Plateforme2(w/3f,h*7/8f - w/50, w-w/3f, h* 7/8f + w/50)
        balle = Balle2( w * 1/2f -50f , h* 2/3f - 50f , 80f,3)
        vide = Vide(0f,hauteur-w/50f,largeur,hauteur)
        transparent = Transparent(0f,h/2f,largeur,h/2f +5)


        lesMalus = arrayListOf(Malus(w/47f + 3*param , marge+w/47f+ 6*param, param))
        lesBonus = arrayListOf(Bonus(w/47f + 9*param , marge+w/47f+ 8*param,param))



        lesMonstres = arrayListOf(
            Monstre2((Random.nextInt(w/50, 1*w -w/50).toFloat()),(Random.nextInt(marge.toInt() - w/50, 1*(h/2-80)).toFloat()),80f),
            //Monstre2((Random.nextInt(w/50 + 100 , 1*w -100).toFloat() - 80 ),(Random.nextInt(w/50 +100, 1*(h*1/2)).toFloat()),80f),
            //Monstre2((Random.nextInt(w/50 + 100, 1*w -100).toFloat() - 80),(Random.nextInt(w/50 +100, 1*(h*1/2)).toFloat()),80f)
        )

        lesParois = arrayOf(
            Parois2(0f, marge+2f, w/50f, hauteur), // gauche
            Parois2(0f, marge+2f, largeur, w/50f+marge+2f), //haut
            Parois2(largeur - w/50f, marge+2f, largeur, hauteur)) //droite


        lesCarres = arrayListOf(

            Carre2(w/47f , marge+param+w/47f, w/47f+ param ,marge+w/47f+ 2*param,1),
            Carre2(w/47f + param, marge+param+w/47f, w/47f+ 2*param,marge+w/47f + 2*param,1),
            Carre2(w/47f + 2*param , marge+param+w/47f , w/47f + 3*param,marge+w/47f + 2*param,1),
            Carre2(w/47f + 3*param , marge+param+w/47f, w/47f + 4*param ,marge+w/47f + 2*param,1),
            //Carre2(w/47f + 4*param , marge+param+w/47f, w/47f + 4*param ,marge+w/47f + 2*param,1),
            //Carre2(w/47f + 5*param , marge+param+w/47f, w/47f + 4*param ,marge+w/47f + 2*param,1),
            //Carre2(w/47f + 6*param , marge+param+w/47f, w/47f + 4*param ,marge+w/47f + 2*param,1),
            Carre2(w/47f + 7*param, marge+param+w/47f, w/47f + 8*param,marge+w/47f + 2*param,1),
            //Carre2(w/47f + 8*param , marge+param+w/47f, w/47f + 4*param ,marge+w/47f + 2*param,1),
            Carre2(w/47f + 9*param , marge+param+w/47f, w/47f + 10*param ,marge+w/47f + 2*param,1),


            Carre2(w/47f + param, marge+w/47f+2*param, w/47f+ 2*param,marge+w/47f+ 3*param,1),
            Carre2(w/47f + 2*param , marge+w/47f+2*param , w/47f + 3*param,marge+w/47f+ 3*param,1),
            //Carre2(w/47f + 3*param , marge+param+w/47f, w/47f + 4*param ,marge+w/47f + 3*param,1),
            Carre2(w/47f + 4*param, marge+w/47f+2*param, w/47f + 5*param,marge+w/47f+ 3*param,1),
            Carre2(w/47f + 5*param , marge+w/47f+2*param, w/47f + 6*param,marge+w/47f+ 3*param,1),
            Carre2(w/47f + 6*param , marge+w/47f+2*param , w/47f + 7*param ,marge+w/47f+ 3*param,1),
            Carre2(w/47f + 7*param, marge+w/47f+2*param, w/47f + 8*param,marge+w/47f+ 3*param,1),
            Carre2(w/47f + 8*param , marge+w/47f+2*param, w/47f + 9*param,marge+w/47f+ 3*param,1),
            //Carre2(w/47f + 9*param , marge+param+w/47f, w/47f + 4*param ,marge+w/47f + 3*param,1),



            Carre2(w/47f , marge+w/47f+ 3*param, w/47f+ param ,marge+w/47f+ 4*param,1),
            //Carre2(w/47f + param, marge+w/47f+ 3*param, w/47f+ 2*param,w/47f+ 4*param,1),
            //Carre2(w/47f + 2*param , marge+w/47f+ 3*param , w/47f + 3*param,w/47f+ 4*param,1),
            Carre2(w/47f + 3*param , marge+w/47f+ 3*param, w/47f + 4*param ,marge+w/47f+ 4*param,1),
            //Carre2(w/47f + 4*param, marge+w/47f+ 3*param, w/47f + 5*param,w/47f+ 4*param,1),
            //Carre2(w/47f + 5*param , marge+w/47f+ 3*param, w/47f + 6*param,w/47f+ 4*param,1),
            Carre2(w/47f + 6*param , marge+w/47f+ 3*param , w/47f + 7*param ,marge+w/47f+ 4*param,1),
            //Carre2(w/47f + 7*param, marge+w/47f+ 3*param, w/47f + 8*param,w/47f+ 4*param,1),
            //Carre2(w/47f + 8*param ,marge+w/47f+ 3*param, w/47f + 9*param,w/47f+ 4*param,1),
            Carre2(w/47f + 9*param , marge+w/47f+ 3*param, w/47f + 10*param ,marge+w/47f+ 4*param,1),

            //Carre2(w/47f , marge+w/47f+ 4*param, w/47f+ param ,marge+w/47f+ 5*param,1),
            Carre2(w/47f + param, marge+w/47f+ 4*param, w/47f+ 2*param,marge+w/47f+ 5*param,1),
            Carre2(w/47f + 2*param , marge+w/47f+ 4*param , w/47f + 3*param,marge+w/47f+ 5*param,1),
            //Carre2(w/47f + 3*param , marge+w/47f+ 4*param, w/47f + 4*param ,marge+w/47f+ 5*param,1),
            //Carre2(w/47f + 4*param, marge+w/47f+ 4*param, w/47f + 5*param,marge+w/47f+ 5*param,1),
            Carre2(w/47f + 5*param , marge+w/47f+ 4*param, w/47f + 6*param,marge+w/47f+ 5*param,1),
            Carre2(w/47f + 6*param , marge+w/47f+ 4*param , w/47f + 7*param ,marge+w/47f+ 5*param,1),
            Carre2(w/47f + 7*param, marge+w/47f+ 4*param, w/47f + 8*param,marge+w/47f+ 5*param,1),
            //Carre2(w/47f + 8*param ,marge+w/47f+ 4*param, w/47f + 9*param,marge+w/47f+ 5*param,1),
            //Carre2(w/47f + 9*param , marge+w/47f+ 4*param, w/47f + 10*param ,marge+w/47f+ 5*param,1),


            Carre2(w/47f , marge+w/47f+ 5*param, w/47f+ param ,marge+w/47f+ 6*param,1),
            //Carre2(w/47f + param, marge+w/47f+ 5*param, w/47f+ 2*param,marge+w/47f+ 6*param,1),
            Carre2(w/47f + 2*param , marge+w/47f+ 5*param , w/47f + 3*param,marge+w/47f+ 6*param,1),
            Carre2(w/47f + 3*param , marge+w/47f+ 5*param, w/47f + 4*param ,marge+w/47f+ 6*param,1),
            Carre2(w/47f + 4*param, marge+w/47f+ 5*param, w/47f + 5*param,marge+w/47f+ 6*param,1),
            //Carre2(w/47f + 5*param , marge+w/47f+ 5*param, w/47f + 6*param,marge+w/47f+ 6*param,1),
            //Carre2(w/47f + 6*param , marge+w/47f+ 5*param , w/47f + 7*param ,marge+w/47f+ 6*param,1),
            //Carre2(w/47f + 7*param, marge+w/47f+ 5*param, w/47f + 8*param,marge+w/47f+ 6*param,1),
            Carre2(w/47f + 8*param ,marge+w/47f+ 5*param, w/47f + 9*param,marge+w/47f+ 6*param,1),
            Carre2(w/47f + 9*param , marge+w/47f+ 5*param, w/47f + 10*param ,marge+w/47f+ 6*param,1),



            //Carre2(w/47f , marge+w/47f+ 6*param, w/47f+ param ,marge+w/47f+ 7*param,1),
            Carre2(w/47f + param, marge+w/47f+ 6*param, w/47f+ 2*param,marge+w/47f+ 7*param,1),
            Carre2(w/47f + 2*param , marge+w/47f+ 6*param , w/47f + 3*param,marge+w/47f+ 7*param,1),
            //Carre2(w/47f + 3*param , marge+w/47f+ 6*param, w/47f + 4*param ,marge+w/47f+ 7*param,1),
            Carre2(w/47f + 4*param, marge+w/47f+ 6*param, w/47f + 5*param,marge+w/47f+ 7*param,1),
            //Carre2(w/47f + 5*param , marge+w/47f+ 6*param, w/47f + 6*param,marge+w/47f+ 7*param,1),
            Carre2(w/47f + 6*param , marge+w/47f+ 6*param , w/47f + 7*param ,marge+w/47f+ 7*param,1),
            Carre2(w/47f + 7*param, marge+w/47f+ 6*param, w/47f + 8*param,marge+w/47f+ 7*param,1),
            //Carre2(w/47f + 8*param ,marge+w/47f+ 6*param, w/47f + 9*param,marge+w/47f+ 7*param,1),
            //Carre2(w/47f + 9*param , marge+w/47f+ 6*param, w/47f + 10*param ,marge+w/47f+ 7*param,1),

            //Carre2(w/47f , marge+w/47f+ 7*param, w/47f+ param ,marge+w/47f+ 8*param,1),
            Carre2(w/47f + param, marge+w/47f+ 7*param, w/47f+ 2*param,marge+w/47f+ 8*param,1),
            //Carre2(w/47f + 2*param , marge+w/47f+ 7*param , w/47f + 3*param,marge+w/47f+ 8*param,1),
            Carre2(w/47f + 3*param , marge+w/47f+ 7*param, w/47f + 4*param ,marge+w/47f+ 8*param,1),
            //Carre2(w/47f + 4*param, marge+w/47f+ 7*param, w/47f + 5*param,marge+w/47f+ 8*param,1),
            Carre2(w/47f + 5*param , marge+w/47f+ 7*param, w/47f + 6*param,marge+w/47f+ 8*param,1),
            //Carre2(w/47f + 6*param , marge+w/47f+ 7*param , w/47f + 7*param ,marge+w/47f+ 8*param,1),
            Carre2(w/47f + 7*param, marge+w/47f+ 7*param, w/47f + 8*param,marge+w/47f+ 8*param,1),
            Carre2(w/47f + 8*param ,marge+w/47f+ 7*param, w/47f + 9*param,marge+w/47f+ 8*param,1),
            Carre2(w/47f + 9*param , marge+w/47f+ 7*param, w/47f + 10*param ,marge+w/47f+ 8*param,1),

            Carre2(w/47f , marge+w/47f+ 8*param, w/47f+ param ,marge+w/47f+ 9*param,1),
//            Carre2(w/47f + param, marge+w/47f+ 8*param, w/47f+ 2*param,marge+w/47f+ 9*param,1),
//            Carre2(w/47f + 2*param , marge+w/47f+ 8*param , w/47f + 3*param,marge+w/47f+ 9*param,1),
//            Carre2(w/47f + 3*param , marge+w/47f+ 8*param, w/47f + 4*param ,marge+w/47f+ 9*param,1),
            Carre2(w/47f + 4*param, marge+w/47f+ 8*param, w/47f + 5*param,marge+w/47f+ 9*param,1),
//            Carre2(w/47f + 5*param , marge+w/47f+ 8*param, w/47f + 6*param,marge+w/47f+ 9*param,1),
            Carre2(w/47f + 6*param , marge+w/47f+ 8*param , w/47f + 7*param ,marge+w/47f+ 9*param,1),
//            Carre2(w/47f + 7*param, marge+w/47f+ 8*param, w/47f + 8*param,marge+w/47f+ 9*param,1),
//            Carre2(w/47f + 8*param ,marge+w/47f+ 8*param, w/47f + 9*param,marge+w/47f+ 9*param,1),
//            Carre2(w/47f + 9*param , marge+w/47f+ 8*param, w/47f + 10*param ,marge+w/47f+ 9*param,1),
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
            canvas.drawText(balle.vie.toString(),50f,110f,viePaint)

            for (parois in lesParois){
                parois.draw(canvas)
            }
            for (carre in lesCarres){
                carre.draw(canvas)
            }

            for (monstres in lesMonstres){
                monstres.draw(canvas)
            }

            for (bonus in lesBonus){
                bonus.draw(canvas)
            }

            for (malus in lesMalus){
                malus.draw(canvas)
            }

            transparent.draw(canvas)
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
            monstres.mangerBalle(balle)
            transparent.Reactionballe(monstres)
        }

        for (parois in lesParois){
            parois.Reactionballe(balle)
        }

        for (carre in lesCarres){
            carre.Reactionballe(balle)
        }

        for (bonus in lesBonus){
            bonus.ReactionBalle(balle,plateforme)
        }

        for (malus in lesMalus){
            malus.ReactionBalle(balle,plateforme)
        }

        if (balle.vie == 0 && balle.diametre != 0f){
            keepdrawing = false
            gameOver = true
            showGameOverDialog("GameOver")
        }

    }


    fun showGameOverDialog(messageId: String) {
        class GameResult: DialogFragment() {
            override fun onCreateDialog(bundle: Bundle?): Dialog {
                val builder = AlertDialog.Builder(getActivity())
                builder.setTitle(messageId)
                builder.setMessage("Nombre de vie: " + balle.vie)
                builder.setPositiveButton("Redemarre le jeu",
                    DialogInterface.OnClickListener { _, _->newGame()}
                )
                return builder.create()
            }
        }
        activity.runOnUiThread(
            Runnable {
                val ft = activity.supportFragmentManager.beginTransaction()
                val prev =
                    activity.supportFragmentManager.findFragmentByTag("dialog")
                if (prev != null) {
                    ft.remove(prev)
                }
                ft.addToBackStack(null)
                val gameResult = GameResult()
                gameResult.setCancelable(false)
                gameResult.show(ft,"dialog")
            }
        )
    }

    fun newGame(){
        balle.reset()
        keepdrawing = true
        if(gameOver) {
            gameOver = false
            thread = Thread(this)
            thread.start()
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