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
    var list = arrayListOf<Int>()
    val mediaori = v.mediaori
    val mediahalo = v.mediahalo
    var runlevel = true

    override fun run() {
        while(runlevel){
            if(level ==1){

            }
            else if (level==2){

            }

            else if (level == 3){

            }
            if (v.gameWin && v.r == 1){
//                mediaPlayer.start()
                level += 1
                v.r = 0
                v.gameWin = false
                when(level){
                    2 -> {
                        v.Jungle = BitmapFactory.decodeResource(v.resources, R.drawable.niveau2)

                        mediaori.start()

                        list.addAll(listOf(27,37))

                        //v.modifieMonstre(2)

                        v.modifieBonus(2)

                        v.modifieMalus(list)

                        v.modifieCarres(list,1)

                        v.color("green")

                        v.resume() // relance le thread car la fonction run du drawingview n'arrete

                    }

                    3 -> {

                        v.Jungle = BitmapFactory.decodeResource(v.resources, R.drawable.niveau3)

                        mediaori.stop()
                        mediahalo.start()

                        list.addAll(listOf(29,40))

                        //v.modifieMonstre(3)

                        v.modifieBonus(4)

                        v.modifieMalus(list)

                        v.modifieCarres(list,2)

                        v.color("red")

                        v.resume()

                      }

                    4 -> {level = 1

                        mediahalo.stop()

                        v.showGameOverDialog("findujeu")

                        v.resume()
                    }

                }

            }
        }
        runlevel = false
    }
}