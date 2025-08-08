package com.pida.imrithysrhymes

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Environment
import android.util.Log
import okio.IOException
import java.io.File

class AudioRecorder(private val context: Context) {
    private var recorder: MediaRecorder? = null
    private var player: MediaPlayer? = null
    private var fileName: String = ""

    fun startRecording(): String {
        val outputDir = context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)
        val file = File.createTempFile("hafalan_", ".3gp", outputDir)
        fileName = file.absolutePath

        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4) // Ganti ini
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)    // Dan ini
            prepare()
            start()
            Log.d("AudioRecorder", "Rekaman disimpan di: $fileName")

        }

        return fileName
    }

    fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

    fun startPlaying(filePath: String) {
        player = MediaPlayer().apply {
            setDataSource(filePath)
            try {
                prepare()
                start()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    fun stopPlaying() {
        player?.release()
        player = null
    }


    fun isPlaying(): Boolean = player?.isPlaying == true
}
