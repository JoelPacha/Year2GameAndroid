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
    var list = arrayListOf<Carre>()
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

                        v.modifiemonstre(v.lesMonstres)

                        //v.lesBonus += Bonus(w/47f + 2*param , marge+w/47f , w/47f + 4*param,marge+w/47f+ e) // n°2 dans la liste des carres drawingview 1


                        for (i in 0..(v.lesCarres).size-1){
                            if (i != 1 || i != 26 || i != 36){  // mettre les n° des carre ou on rjaoute des bonus pour ne pas avoir des cases superposer
                                list.add(v.lesCarres[i])
                                v.lesCarres[i].resistance += 1  // rajoute +1 a la resistance des blocs
                            }

                        }
                        v.lesCarres = list
                        v.carreCasses = BooleanArray(list.size){false}   // Re-inité la liste des carrecasses car la liste des carre est modifié



                        v.resume() // relance le thread car la fonction run du drawingview n'arrete

                    }

                    3 -> {

                        v.Jungle = BitmapFactory.decodeResource(v.resources, R.drawable.niveau3)

                        v.resume()

                      }

                    4 -> {level = 1
                        v.Jungle = BitmapFactory.decodeResource(v.resources, R.drawable.niveau1)
                        v.carreCasses = BooleanArray(v.lesCarres.size){false}
                        v.resume()
                        lesMonstreSupp = arrayListOf()
                    }

                }

            }
        }
        runlevel = false
    }
}