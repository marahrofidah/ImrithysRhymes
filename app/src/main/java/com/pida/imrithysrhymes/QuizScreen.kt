package com.pida.imrithysrhymes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pida.imrithysrhymes.ui.theme.WinkySansFont

@Composable
fun QuizScreen(
    quiz: Quiz,
    streak: Int,
    navController: NavHostController,
    quizViewModel: QuizViewModel,
    onAnswerSelected: (Boolean) -> Unit,
    onRetry: () -> Unit
) {
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isAnswered by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }
    var currentProgress by remember { mutableStateOf(0) }
    val maxProgress = 254
    var showCorrectDialog by remember { mutableStateOf(false) }
    var showWrongDialog by remember { mutableStateOf(false) }


    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        containerColor = Color.White
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Quiz\nHari Ini",
                        fontFamily = WinkySansFont,
                        fontSize = 50.sp,
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            lineHeight = 50.sp
                        ),
                        color = Color(0xFF3A327C)
                    )
                    Image(
                        painter = painterResource(R.drawable.ic_timer),
                        contentDescription = "Timer",
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(160.dp)
                            .offset(y = 50.dp, x =20.dp)
                    )
                }

                Text(
                    text = "Jawab pertanyaan\nhari ini untuk menjaga\nfocus track-mu!",
                    style = MaterialTheme.typography.bodyMedium.copy(lineHeight = 24.sp),
                    fontFamily = WinkySansFont,
                    fontSize = 21.sp,
                    color = Color(0xFF3A327C),
                    modifier = Modifier.offset(y = -38.dp)
                )

                // Bait Card
                Card(
                    shape = RoundedCornerShape(40.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF3B513B)),
                    elevation = CardDefaults.cardElevation(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -40.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = quiz.bait,
                            color = Color.White,
                            fontFamily = WinkySansFont,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Pertanyaan Card
                Card(
                    shape = RoundedCornerShape(40.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFDBFFD9)),
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -40.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Text(
                            "Pertanyaan:",
                            style = MaterialTheme.typography.titleMedium,
                            fontFamily = WinkySansFont,
                            color = Color(0xFF3A327C),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = quiz.question,
                            fontWeight = FontWeight.Bold,
                            fontFamily = WinkySansFont,
                            color = Color(0xFF3A327C),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        // Pilihan Jawaban
                        quiz.options.forEach { option ->
                            Button(
                                onClick = {
                                    selectedOption = option
                                    isAnswered = true
                                    isCorrect = option == quiz.correctAnswer

                                    if (option == quiz.correctAnswer) {
//                                        quizViewModel.incrementProgress() // ✅ update progress
                                        showCorrectDialog = true
                                    } else {
                                        showWrongDialog = true
                                    }

                                    onAnswerSelected(isCorrect)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isAnswered && option == selectedOption) {
                                        if (option == quiz.correctAnswer) Color(0xFF4CAF50)
                                        else Color(0xFFE53935)
                                    } else Color.White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .drawBehind {
                                        val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                        val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                        val cornerRadius = 40.dp.toPx()

                                        drawRoundRect(
                                            color = shadowColorLight,
                                            topLeft = Offset(-10f, -10f),
                                            size = size,
                                            cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                                        )
                                        drawRoundRect(
                                            color = shadowColorDark,
                                            topLeft = Offset(6f, 6f),
                                            size = size,
                                            cornerRadius = CornerRadius(cornerRadius, cornerRadius)
                                        )
                                    },
                                enabled = !isAnswered
                            ) {
                                Text(option, fontFamily = WinkySansFont, color = Color(0xFF3A327C))
                            }
                        }

                        // Alert Salah
                        if (showWrongDialog) {
                            AlertDialog(
                                containerColor = Color(0xFF3B513B),
                                onDismissRequest = { showWrongDialog = false },
                                modifier = Modifier
                                    .drawBehind {
                                        val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                        val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                        val cornerRadius = 30.dp.toPx()
                                        val blurRadius = 10.dp.toPx()

                                        // Shadow terang (atas kiri)
                                        drawRoundRect(
                                            color = shadowColorLight,
                                            topLeft = Offset(10f, 10f),
                                            size = size,
                                            cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                            blendMode = BlendMode.SrcOver
                                        )

                                        // Shadow gelap (bawah kanan)
                                        drawRoundRect(
                                            color = shadowColorDark,
                                            topLeft = Offset(6f, 6f),
                                            size = size,
                                            cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                            blendMode = BlendMode.SrcOver
                                        )
                                    },
                                title = {
                                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                        Text(
                                            "Upss...\nJawaban salah",
                                            fontFamily = WinkySansFont,
                                            textAlign = TextAlign.Center,
                                            color = Color.White,
                                            fontSize = 24.sp
                                        )
                                    }
                                },
                                text = {
                                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                        Text(
                                            "Coba ulangi lagi ya!",
                                            fontFamily = WinkySansFont,
                                            textAlign = TextAlign.Center,
                                            color = Color.White,
                                            fontSize = 18.sp
                                        )
                                    }
                                },
                                confirmButton = {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Button(onClick = {
                                            showWrongDialog = false
                                            isAnswered = false
                                            selectedOption = null
                                            onRetry()
                                        },modifier = Modifier
                                            .drawBehind {
                                                val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                                val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                                val cornerRadius = 60.dp.toPx()
                                                val blurRadius = 10.dp.toPx()

                                                // Shadow terang (atas kiri)
                                                drawRoundRect(
                                                    color = shadowColorLight,
                                                    topLeft = Offset(5f, 2f),
                                                    size = size,
                                                    cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                                    blendMode = BlendMode.SrcOver
                                                )

                                                // Shadow gelap (bawah kanan)
                                                drawRoundRect(
                                                    color = shadowColorDark,
                                                    topLeft = Offset(2f, 2f),
                                                    size = size,
                                                    cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                                    blendMode = BlendMode.SrcOver
                                                )
                                            },colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFFDBFFD9))) {
                                            Text("Ulangi", fontFamily = WinkySansFont, color = Color(0xFF3A327C), fontSize = 18.sp)
                                        }
                                    }
                                }
                            )
                        }
                        if (showCorrectDialog) {
                            AlertDialog(
                                containerColor = Color(0xFF3B513B),
                                onDismissRequest = { showCorrectDialog = false },
                                modifier = Modifier
                                    .drawBehind {
                                        val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                        val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                        val cornerRadius = 30.dp.toPx()
                                        val blurRadius = 10.dp.toPx()

                                        // Shadow terang (atas kiri)
                                        drawRoundRect(
                                            color = shadowColorLight,
                                            topLeft = Offset(10f, 10f),
                                            size = size,
                                            cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                            blendMode = BlendMode.SrcOver
                                        )

                                        // Shadow gelap (bawah kanan)
                                        drawRoundRect(
                                            color = shadowColorDark,
                                            topLeft = Offset(6f, 6f),
                                            size = size,
                                            cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                            blendMode = BlendMode.SrcOver
                                        )
                                    },
                                title = {
                                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                        Text(
                                            "Yeayy!! 🎉",
                                            fontFamily = WinkySansFont,
                                            textAlign = TextAlign.Center,
                                            color = Color.White
                                        )
                                    }
                                },
                                text = {
                                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                                        Text(
                                            "Jawaban benar!\nFokus track-mu bertambah 💪",
                                            fontFamily = WinkySansFont,
                                            textAlign = TextAlign.Center,
                                            color = Color.White,
                                            fontSize = 18.sp
                                        )
                                    }
                                },
                                confirmButton = {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),

                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Button(onClick = {
                                            showCorrectDialog = false
                                            // Balik ke quiz yang sama (tetap tidak bisa jawab lagi)
                                        }, modifier = Modifier
                                            .drawBehind {
                                                val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                                val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                                val cornerRadius = 60.dp.toPx()
                                                val blurRadius = 10.dp.toPx()

                                                // Shadow terang (atas kiri)
                                                drawRoundRect(
                                                    color = shadowColorLight,
                                                    topLeft = Offset(5f, 3f),
                                                    size = size,
                                                    cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                                    blendMode = BlendMode.SrcOver
                                                )

                                                // Shadow gelap (bawah kanan)
                                                drawRoundRect(
                                                    color = shadowColorDark,
                                                    topLeft = Offset(2f, 2f),
                                                    size = size,
                                                    cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                                    blendMode = BlendMode.SrcOver
                                                )
                                            },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = Color(0xFFDBFFD9))) {
                                            Text("Oke", fontFamily = WinkySansFont, color = Color(0xFF3A327C))
                                        }

                                        Button(onClick = {
                                            showCorrectDialog = false
                                            navController.navigate("home") {
                                                popUpTo("quiz") { inclusive = true }
                                            }
                                        }, modifier = Modifier
                                            .drawBehind {
                                                val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                                val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                                val cornerRadius = 60.dp.toPx()
                                                val blurRadius = 10.dp.toPx()

                                                // Shadow terang (atas kiri)
                                                drawRoundRect(
                                                    color = shadowColorLight,
                                                    topLeft = Offset(5f, 5f),
                                                    size = size,
                                                    cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                                    blendMode = BlendMode.SrcOver
                                                )

                                                // Shadow gelap (bawah kanan)
                                                drawRoundRect(
                                                    color = shadowColorDark,
                                                    topLeft = Offset(3f, 3f),
                                                    size = size,
                                                    cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                                    blendMode = BlendMode.SrcOver
                                                )
                                            }
                                            ,colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFFDBFFD9))) {
                                            Text("Back to Home", fontFamily = WinkySansFont, color = Color(0xFF3A327C))
                                        }
                                    }
                                }
                            )
                        }

                    }}}
                        // Dekorasi gambar pojok kanan atas
            Box(
                modifier = Modifier
                    .width(129.dp)
                    .height(150.dp)
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp, end = 8.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.rhymes),
                    contentDescription = "Rhymes Text",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(y = -40.dp)
                        .size(129.dp, 150.dp)
                )
                Image(
                    painter = painterResource(R.drawable.imrithys),
                    contentDescription = "Imrithys Text",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(y = -70.dp, x = -20.dp)
                        .size(129.dp, 150.dp)
                )
                Image(
                    painter = painterResource(R.drawable.person1),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = -95.dp, y = -20.dp)
                        .size(70.dp)
                )
            }
        }
    }
}
