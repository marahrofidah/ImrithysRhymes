import com.pida.imrithysrhymes.Quiz
import java.time.LocalDate

object QuizRepository {
    private val quizzes = listOf(
        Quiz(
            id = 1,
            bait = "Bait:\n اَلحْـَمْدُ للهِ الَّـذِى قَدْ وَفَقَ * لِلْعِلْمِ خَيْرَ خَلْقِهِ وَللِتُّقَى",
            question = "Berdasarkan Bait di atas kepada siapa taufiq Allah dilimpahkan?",
            options = listOf(
                "Kepada seluruh manusia.",
                "Kepada para ulama besar saja.",
                "Kepada hamba-hamba terbaik-Nya.",
                "Kepada para nabi dan rasul."
            ),
            correctAnswer = "Kepada hamba-hamba terbaik-Nya.",
            streak = 1
        ),
        Quiz(
            id = 2,
            bait = "Bait:\n  فَأَشْرِبتُ مَعْنَى ضَمِيرِ الشَّانِ * فَأَعْرَبتُ فِي الْحَانِ بِالْأَلْحَانِ",
            question = "Apa makna ḍamīr asy-sya’n yang disebut di bait ini?",
            options = listOf(
                "Laa ilaaha illallaah.",
                "Bismillahirrahmanirrahim.",
                "Subhanallah walhamdulillah.",
                "Astaghfirullah."
            ),
            correctAnswer = "Laa ilaaha illallaah.",
            streak = 2
        ),
        Quiz(
            id = 3,
            bait = "Bait:\n ثمَُّ الصَّلاةَُ مَعَ سَلامٍَ لائقَِِ * عَلىَ النبَّيِِ أفَْصَحِ الْخَلائقَِِ",
            question = "Siapa yang dimaksud Nabi paling fasih lisannya?",
            options = listOf(
                "Nabi Musa.",
                "Nabi Muhammad.",
                "Nabi Ibrahim.",
                "Nabi Isa."
            ),
            correctAnswer = "Nabi Muhammad.",
            streak = 3
        )
    )

    fun getTodayQuiz(): Quiz {
        val today = java.util.Calendar.getInstance()
        val dayOfYear = today.get(java.util.Calendar.DAY_OF_YEAR)
        val index = dayOfYear % quizzes.size
        return quizzes[index]
    }
}
