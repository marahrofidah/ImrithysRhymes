package com.pida.imrithysrhymes

data class Quiz(
    val id: Int,
    val bait: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val streak: Int
)
