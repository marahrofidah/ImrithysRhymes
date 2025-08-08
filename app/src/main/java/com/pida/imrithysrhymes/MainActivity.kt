package com.pida.imrithysrhymes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pida.imrithysrhymes.ui.theme.ImrithysRhymesTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImrithysRhymesTheme {
                var splashFinished by remember { mutableStateOf(false) }
                var username by remember { mutableStateOf("") }
                val navController = rememberNavController()

                if (!splashFinished) {
                    AnimatedSplashScreen { inputName ->
                        username = inputName
                        splashFinished = true
                    }
                } else {
                    MainNavigation(username, navController)
                }

            }
        }
    }
}

@Composable
fun MainNavigation(username: String, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeScreen(username = username, navController = navController)
        }
        composable("dengarkan_syair") {
            DengarkanSyairScreen(navController = navController)
        }
        composable("kitab") {
            BukaKitabScreen(navController = navController)
        }
        composable("user") {
            UserScreen(navController = navController)
        }
        composable("setor_hafalan") {
            SetorHafalanScreen(navController = navController)
        }
        composable("main_quiz") {
            val quiz = remember { QuizRepository.getTodayQuiz() }
            val streak = quiz.streak // atau kamu bisa pakai state management lain nanti

            QuizScreen(
                quiz = quiz,
                streak = streak,
                navController = navController,
                onAnswerSelected = { isCorrect ->
                    // TODO: logika setelah jawab
                },
                onRetry = {
                    // TODO: logika jika user retry
                }
            )
        }


    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(username = "pidaa", navController = navController)
}
