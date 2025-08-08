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
    onAnswerSelected: (Boolean) -> Unit,
    onRetry: () -> Unit
) {
    var selectedOption by remember { mutableStateOf<String?>(null) }
    var isAnswered by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        containerColor = Color.White
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White)
        ) {
            // ====== Kolom utama konten quiz ======
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()), // ➡ ini yang bikin scroll
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
            Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween, // pisahkan kiri & kanan
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Teks Quiz Hari Ini
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

                    // Gambar timer di kanan
                    Image(
                        painter = painterResource(id = R.drawable.ic_timer), // ganti ke resource kamu
                        contentDescription = "Timer",
                        modifier = Modifier.fillMaxWidth()
                            .size(140.dp) .offset(y = 50.dp, x = 20.dp)// ukuran timer
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

                Card(
                    shape = RoundedCornerShape(40.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE5A6)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                    modifier = Modifier.fillMaxWidth().offset(y = -40.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // 🔥 Ikon api + background kuning
                            Box(
                                modifier = Modifier.size(68.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.lb_api), // background kuning
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize()
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.api), // ikon api
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .align(Alignment.Center)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            // 📝 Semua teks dalam 1 column di sebelah kanan ikon
                            Column {
                                Text(
                                    text = "Focus Track:",
                                    fontFamily = WinkySansFont,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF3A327C)
                                )
                                Text(
                                    text = "Stay on track dengan Quiz harian + hafalan\nGagal sehari, balik ke awal 👀",
                                    fontSize = 13.5.sp,
                                    fontFamily = WinkySansFont,
                                    color = Color(0xFF3A327C),
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        LinearProgressIndicator(
                            progress = { 0f / 254f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .clip(RoundedCornerShape(100.dp)),
                            color = Color(0xFFFFC107),
                            trackColor = Color(0xFFE0E0E0)
                        )

                        Text(
                            text = "0/254",
                            fontSize = 12.sp,
                            color = Color.DarkGray,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }

                }


                Card(
                    shape = RoundedCornerShape(40.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF3B513B)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-40).dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center // ✅ Posisikan teks di tengah secara penuh
                    ) {
                        Text(
                            text = quiz.bait,
                            color = Color.White,
                            fontFamily = WinkySansFont,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                Card(
                    shape = RoundedCornerShape(40.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFDBFFD9)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = -40.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Text("Pertanyaan:", style = MaterialTheme.typography.titleMedium,  fontFamily = WinkySansFont,
                            color = Color(0xFF3A327C),  textAlign = TextAlign.Center)
                        Text(
                            text = quiz.question,
                            fontWeight = FontWeight.Bold,
                            fontFamily = WinkySansFont,
                            color = Color(0xFF3A327C),
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        quiz.options.forEach { option ->
                            Button(
                                onClick = {
                                    selectedOption = option
                                    isAnswered = true
                                    isCorrect = option == quiz.correctAnswer
                                    onAnswerSelected(isCorrect)
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isAnswered && option == selectedOption) {
                                        if (option == quiz.correctAnswer) Color(0xFF4CAF50) else Color(
                                            0xFFE53935
                                        )
                                    } else Color.White
                                ),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .drawBehind {
                                        val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                        val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                        val cornerRadius = 30.dp.toPx()
                                        val blurRadius = 10.dp.toPx()

                                        // Shadow terang (atas kiri)
                                        drawRoundRect(
                                            color = shadowColorLight,
                                            topLeft = Offset(-10f, -10f),
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
                                enabled = !isAnswered
                            ) {
                                Text(option,  fontFamily = WinkySansFont,
                                    color = Color(0xFF3A327C),)
                            }
                        }

                        if (isAnswered && !isCorrect) {
                            AlertDialog(
                                onDismissRequest = {},
                                title = { Text("Jawaban Salah") },
                                text = { Text("Coba ulangi lagi ya!") },
                                confirmButton = {
                                    Button(onClick = {
                                        isAnswered = false
                                        selectedOption = null
                                        onRetry()
                                    }) {
                                        Text("Ulangi")
                                    }
                                }
                            )
                        }
                    }
                }}


            // ====== Tumpukan gambar di pojok kanan atas ======
            Box(
                modifier = Modifier
                    .width(129.dp)
                    .height(150.dp)
                    .align(Alignment.TopEnd) // Geser ke pojok kanan atas
                    .padding(top = 8.dp, end = 8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rhymes),
                    contentDescription = "Rhymes Text",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(y = -40.dp, x = 0.dp)
                        .size(width = 129.dp, height = 150.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.imrithys),
                    contentDescription = "Imrithys Text",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(y = (-70).dp, x = (-20).dp)
                        .size(width = 129.dp, height = 150.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.person1),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(x = -95.dp, y = -20.dp)
                        .size(width = 70.dp, height = 70.dp)
                )
            }
        }
    }
}
