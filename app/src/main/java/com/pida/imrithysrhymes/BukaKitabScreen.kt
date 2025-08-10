package com.pida.imrithysrhymes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.pida.imrithysrhymes.ui.theme.WinkySansFont

// BukaKitabScreen.kt
@Composable
fun BukaKitabScreen(
    navController: NavHostController,
    onBabClick: (String) -> Unit
) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Gabungkan header dan gambar dalam satu Box untuk mengatur posisi tumpang tindih
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight() // Tinggi disesuaikan dengan konten
            ) {
                // Bagian gambar pojok kanan atas
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 8.dp, end = 8.dp)
                ) {
                    Image(
                        painter = painterResource(R.drawable.rhymes),
                        contentDescription = "Rhymes Text",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = -20.dp)
                            .size(129.dp, 150.dp)
                            .zIndex(1f)
                    )
                    Image(
                        painter = painterResource(R.drawable.imrithys),
                        contentDescription = "Imrithys Text",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = -50.dp, x = -10.dp)
                            .size(129.dp, 150.dp)
                            .zIndex(2f)
                    )
                    Image(
                        painter = painterResource(R.drawable.person1),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = -85.dp)
                            .size(70.dp)
                            .zIndex(3f)
                    )
                }

                // Header
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 60.dp) // Sesuaikan padding atas agar sejajar
                        .align(Alignment.TopStart),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_book),
                        contentDescription = "Book",
                        modifier = Modifier
                            .size(145.dp)
                    )
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .offset(y = 15.dp) // Geser ke bawah sedikit agar pas dengan buku
                    ) {
                        Text(
                            fontFamily = WinkySansFont,
                            color = Color(0xFF3A327C),
                            fontSize = 35.sp,
                            text = "Buka Kitab",
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = "Pilih bab untuk dibaca dalam bentuk PDF.",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF3A327C),
                            fontSize = 20.sp,
                            lineHeight = 30.sp
                        )
                    }
                }
            }

            // Spacer untuk memberi jarak antara header dan LazyColumn
            Spacer(modifier = Modifier.height(10.dp))

            // List Bab (LazyColumn) akan mengisi sisa ruang
            val babList = listOf(
                "Pembukaan – المقدمة",
                "Bab Kalam – باب الكلام",
                "Bab I’rob – باب الإعراب",
                "Bab Alamat I’rob – باب علامات الإعراب",
                "Bab Alamat Nashob – باب علامات النصب",
                "Bab Alamat jer – باب علامات الخفض",
                "Bab tanda-tanda Jazm – باب علامات الجزم",
                "Fasal – فَصْلٌ",
                "Bab makrifat dan nakirah – باب المَعْرِفَةِ والنَّكِرَة"
            )

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                items(babList.size) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .drawBehind {
                                val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                val cornerRadius = 40.dp.toPx()
                                val blurRadius = 10.dp.toPx()

                                // Shadow terang (atas kiri)
                                drawRoundRect(
                                    color = shadowColorLight,
                                    topLeft = Offset(20f, -10f),

                                    cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                    blendMode = BlendMode.SrcOver
                                )

                                // Shadow gelap (bawah kanan)
                                drawRoundRect(
                                    color = shadowColorDark,
                                    topLeft = Offset(6f, 20f),
//                                    size = size,
                                    cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                    blendMode = BlendMode.SrcOver
                                )
                            }
                            .padding(vertical = 6.dp)
                            .clickable { onBabClick(babList[index]) },
                        shape = RoundedCornerShape(50),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFC1CC))
                    ) {
                        Text(
                            fontFamily = WinkySansFont,
                            fontSize = 18.sp,
                            text = babList[index],
                            modifier = Modifier
                                .fillMaxWidth() // Tambahkan modifier ini
                                .padding(vertical = 12.dp, horizontal = 20.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}