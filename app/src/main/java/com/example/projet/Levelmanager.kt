package com.example.projet

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.*
import android.media.MediaPlayer
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import kotlin.random.Random


class Levelmanager(var v : DrawingView) : Runnable{
    var level = 1
    var w = v.largeur.toInt()
    var h = v.hauteur.toInt()
    var marge = v.marge
    var param = v.param
    var e  = v.e
    var diametre = v.diametre
    var list = arrayListOf<Int>()
    var lesMonstreSupp = arrayListOf<Monstre>()
//    val mediaPlayer = MediaPlayer.create(this,R.raw.youwin)
    var runlevel = true

    override fun run() {
        while(runlevel){
            if (v.gameWin && v.r == 1){
//                mediaPlayer.start()
                level += 1
                v.r = 0
                v.gameWin = false
                when(level){
                    2 -> {
                        v.Jungle = BitmapFactory.decodeResource(v.resources, R.drawable.niveau2)

                        list.addAll(listOf(2,27,37))

                        v.modifieMonstre(2)

                        v.modifieEffet(list)

                        v.modifieCarres(list)

                        v.resume() // relance le thread car la fonction run du drawingview n'arrete

                    }

                    3 -> {

                        v.Jungle = BitmapFactory.decodeResource(v.resources, R.drawable.niveau3)

                        list.clear()

                        list.addAll(listOf(10,18,40))

                        v.modifieMonstre(3)

                        v.modifieEffet(list)

                        v.modifieCarres(list)

                        v.resume()

                      }

                    4 -> {level = 1

                        v.Jungle = BitmapFactory.decodeResource(v.resources, R.drawable.niveau1)

                        v.resume()

                        v.showGameOverDialog("fin")
                    }

                }

            }
        }
        runlevel = false
    }
}