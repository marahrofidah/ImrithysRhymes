package com.pida.imrithysrhymes

object QuizRepository {
    fun getTodayQuiz(): Quiz {
        return Quiz(
            id = 1,
            bait = "Bait:\n اَلحْـَمْدُ للهِ الَّـذِى قَدْ وَفَقَ * لِلْعِلْمِ خَيْرَ خَلْقِهِ وَللِتُّقَى",
            question = "Apa maksud dari bait syair di atas?",
            options = listOf(
                "Allah memberikan ilmu dan taqwa kepada semua manusia",
                "Allah memuji makhluk terbaik karena ketakwaannya",
                "Allah memberi taufiq kepada makhluk terbaik-Nya (Nabi Muhammad SAW)"
            ),
            correctAnswer = "Allah memberi taufiq kepada makhluk terbaik-Nya (Nabi Muhammad SAW)",
            streak = 0 // atau nilai streak awalnya
        )
    }}
