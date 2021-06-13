package com.countutilmatch.countmatch.utils

import android.content.Context
import android.media.MediaPlayer
import com.countutilmatch.countmatch.R

class AudioManager (private val context: Context){

    private lateinit var mediaPlayer: MediaPlayer

    fun startSound(){
        mediaPlayer = MediaPlayer.create(context, R.raw.button_click);
        mediaPlayer.start()
    }

    fun startDeleteSound(){
        mediaPlayer = MediaPlayer.create(context, R.raw.delete);
        mediaPlayer.start()
    }
}