package com.pida.imrithysrhymes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    var currentProgress by mutableStateOf(0)
        private set

    val maxProgress = 254

    fun incrementProgress() {
        if (currentProgress < maxProgress) {
            currentProgress++
        }
    }

    fun resetProgress() {
        currentProgress = 0
    }
}