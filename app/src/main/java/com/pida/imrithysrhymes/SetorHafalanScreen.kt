package com.pida.imrithysrhymes

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pida.imrithysrhymes.ui.theme.WinkySansFont

@Composable
fun SetorHafalanScreen(navController: NavHostController) {
    var selectedBab by remember { mutableStateOf("Pembukaan – المقدمة") }
    var isRecording by remember { mutableStateOf(false) }
    var isRecordingStopped by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    var showPopup by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val babList = listOf(
        "Pembukaan – المقدمة",
        "Bab Kalam – باب الكلام",
        "Bab I’rob – باب الإعراب"
    )

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        containerColor = Color.White
    ) { _ ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Logo
            // Logo Header Atas
            Box(
                modifier = Modifier
                    .fillMaxWidth() // Gunakan seluruh lebar layar
            ) {
                Box(
                    modifier = Modifier
                        .width(129.dp)
                        .height(150.dp)
                        .align(Alignment.TopEnd) // Geser ke pojok kanan atas
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.rhymes),
                        contentDescription = "Rhymes Text",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = 0.dp, x = 0.dp)
                            .size(width = 129.dp, height = 150.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.imrithys),
                        contentDescription = "Imrithys Text",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = (-30).dp, x = (-20).dp)
                            .size(width = 129.dp, height = 150.dp)
                    )
                }
            }


// Gambar Orang + Teks
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().offset(y = -70.dp)
            ) {
                // Gambar orang besar
                Image(
                    painter = painterResource(id = R.drawable.ic_person1),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp).offset(y = -10.dp)
                )
//                Spacer(modifier = Modifier.width(5.dp))

                // Teks di samping gambar orang
                Column {
                    Text("Setor Hafalan", fontSize = 32.sp, fontFamily = WinkySansFont, fontWeight = FontWeight.Bold, color = Color(0xFF3A327C))
                    Text(
                        "Rekam hafalanmu lalu kirim agar Focus Track tetap aman",
                        fontSize = 17.sp,
                        fontFamily = WinkySansFont,
                        color = Color (0xFF3A327C)
                    )
                }
            }



            // Dropdown
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .offset(y = -60.dp)
                    .drawBehind {
                        val shadowColorLight = Color.White.copy(alpha = 0.3f)
                        val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                        val cornerRadius = 16.dp.toPx()
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
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF5E0)) // warna latar card
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Pilih Bab",
                        fontSize = 24.sp,
                        color = Color (0xFF3A327C),
                        fontFamily = WinkySansFont,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Bungkus Dropdown dengan Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp)
                            .drawBehind {
                                val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                val cornerRadius = 16.dp.toPx()
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
                            }, // jarak atas
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color.LightGray),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        var dropdownWidth by remember { mutableStateOf(0) }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .onGloballyPositioned { coordinates ->
                                    dropdownWidth = coordinates.size.width
                                }
                                .clickable { expanded = true }
                                .padding(16.dp)
                        ) {
                            Text(
                                text = selectedBab,
                                fontSize = 22.sp,
                                color = Color (0xFF3A327C),
                                fontFamily = WinkySansFont,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )

                            DropdownMenu(
                                expanded = expanded,
                                onDismissRequest = { expanded = false },
                                offset = DpOffset(x = -16.dp, y = 0.dp), // ⬅️ biar sejajar, ga ke kanan
                                modifier = Modifier
                                    .width(with(LocalDensity.current) { dropdownWidth.toDp() })
                                    .background(Color.White)
                            ) {
                                babList.forEach { bab ->
                                    DropdownMenuItem(
                                        text = {
                                            Box(
                                                modifier = Modifier.fillMaxWidth(),
                                                contentAlignment = Alignment.Center
                                            )  {
                                                Text(
                                                    text = bab,
                                                    style = TextStyle(
                                                        fontSize = 22.sp,
                                                        fontFamily = WinkySansFont,
                                                        color = Color (0xFF3A327C),
                                                        textAlign = TextAlign.Center,
                                                        textDirection = TextDirection.ContentOrLtr // ✅ Biar ga ditarik RTL
                                                ))
                                            }
                                        },
                                        onClick = {
                                            selectedBab = bab
                                            expanded = false
                                        }
                                    )

                                }
                            }
                        }}}}



                        // Rekaman UI
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .offset(y = -60.dp)
                    .drawBehind {
                        val shadowColorLight = Color.White.copy(alpha = 0.3f)
                        val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                        val cornerRadius = 16.dp.toPx()
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
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF5E0)),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                when {
                    !isRecording && !isRecordingStopped -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            IconButton(
                                onClick = { isRecording = true },
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(Color(0xFFECECEC), shape = CircleShape)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.mic),
                                    contentDescription = "Mulai Rekam",
                                    tint = Color(0xFF8B5E3C),
                                    modifier = Modifier.size(80.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                "Tekan untuk mulai merekam",
                                fontSize = 24.sp,
                                fontFamily = WinkySansFont,
                                color = Color(0xFF3A327C)
                            )
                        }
                    }

                    isRecording -> {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(), // Isi lebar penuh
                            contentAlignment = Alignment.Center // Tengahkan isinya
                        ){
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.sound),
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                            IconButton(onClick = { isPlaying = !isPlaying }) {
                                Image(
                                    painter = painterResource(id = if (isPlaying) R.drawable.stop else R.drawable.play),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                            IconButton(onClick = {
                                isRecording = false
                                isRecordingStopped = true
                                isPlaying = false
                            }) {
                                Image(
                                    painter = painterResource(id = R.drawable.tutup_rekaman),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                    }}

                    isRecordingStopped -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            IconButton(onClick = { isPlaying = !isPlaying }) {
                                Image(
                                    painter = painterResource(id = if (isPlaying) R.drawable.play else R.drawable.stop),
                                    contentDescription = null,
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        }
                    }
                }
            }


            Spacer(modifier = Modifier.height(40.dp))

                        // Tombol Kirim Hafalan
                        Button(
                            onClick = {
                                showPopup = true
                                isRecording = false
                                isRecordingStopped = false
                                isPlaying = false
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B5E3C)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .drawBehind {
                                    val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                    val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                    val cornerRadius = 16.dp.toPx()
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
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.pesawat_kertas),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                "Kirim Hafalan",
                                fontSize = 26.sp,
                                fontFamily = WinkySansFont,
                                color = Color.White
                            )
                        }


                        // Popup Berhasil
                        if (showPopup) {
                            AlertDialog(
                                onDismissRequest = { showPopup = false },
                                confirmButton = {
                                    Button(onClick = { showPopup = false }) {
                                        Text("Oke")
                                    }
                                },
                                icon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.berhasil),
                                        contentDescription = null
                                    )
                                },
                                title = { Text("Yeayy!!") },
                                text = { Text("Hafalan berhasil dikirim!!") }
                            )
                        }
                    }
                }
            }


