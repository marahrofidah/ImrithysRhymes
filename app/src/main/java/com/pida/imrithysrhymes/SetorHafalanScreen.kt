package com.pida.imrithysrhymes

import android.Manifest
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import com.pida.imrithysrhymes.ui.theme.WinkySansFont
import androidx.compose.runtime.LaunchedEffect


@Composable
fun SetorHafalanScreen(navController: NavHostController) {
    var selectedBab by remember { mutableStateOf("Pembukaan – المقدمة") }
    var isRecording by remember { mutableStateOf(false) }
    var isRecordingStopped by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    var isPaused by remember { mutableStateOf(false) }
    var showPopup by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val recorder = remember { AudioRecorder(context) }
    var audioFilePath by remember { mutableStateOf("") }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (!isGranted) {
                Toast.makeText(context, "Izin diperlukan untuk merekam audio", Toast.LENGTH_SHORT).show()
            }
        }
    )

    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
        }
    }

    val babList = listOf(
        "Pembukaan – المقدمة",
        "Bab Kalam – باب الكلام",
        "Bab I’rob – باب الإعراب",
        "Bab Alamat I’rob – بَابُ عَلَامَاتِ الإِعْرَابِ",
        "Bab Alamat Nashob – بَابُ عَلَامَاتِ النَّصْبِ",
        "Bab Alamat Jer – بَابُ عَلَامَاتِ الخَفْضِ",
        "Bab tanda-tanda Jazam – بَابُ عَلَامَاتِ الجَزْمِ",
        "Fasal – فَصْلٌ",
        "Bab Makrifat dan Nakirah – بَابُ المَعْرِفَةِ وَالنَّكِرةِ",
        "Bab Fiil-fiil – بَابُ الأَفْعَالِ",
        "Bab I’rab Fiil – بَابُ إِعْرَابِ الْفِعْلِ",
        "Bab isim-isim yang dibaca Rafa – بَابُ مَرْفَوعَاتِ الأَسْمَاءِ",
        "Bab Naibul Fail – بَابُ نَائِبِ الْفَاعِلِ",
        "Bab Mubtada dan Khobar – بَابُ المُبْتَدَإ وَالْخَبَر",
        "Kana dan Saudar-saudaranya – كان وأخواتها",
        "Inna dan saudara-saudaranya – إن وأخواتها",
        "Dzanna dan saudara-saudaranya – ظن وأخواتها",
        "Bab Na'at – بَابُ النَّعْتِ",
        "Bab Ataf – بَابُ الْعَطْفِ",
        "Bab Taukid – بَابُ التَّوكِيدِ",
        "Bab Badal – بَابُ الْبَدَلِ",
        "Bab isim-isim yang dibaca nashob – بَابُ مَنْصُوبَاتِ الأَسْمَاءِ",
        "Bab Masdar – بَابُ المَصْدَرِ",
        "Bab Dhorof – بَاب الظَرْفِ",
        "Bab Hal – بَابُ الحَالِ",
        "Bab Tamyiz – بَابُ التَّمْيِيزِ",
        "Bab Istisna – بَابُ الاُسْتِثْنَاءِ",
        "Bab la  yang beramal seperti amal inna – بَابُ لاَ الْعَامِلَةِ عَمَلَ إِنَّ",
        "Bab Munada – بَابُ النِّدَاءِ",
        "Bab Maful Li ajlih – بَابُ المَفْعُولِ لأَجْلِهِ",
        "Bab Maful Maah – بَابُ المَفْعُولِ مَعَهُ",
        "Bab isim-isim yang dibaca jer – بَابُ مَخْفوضَاتِ الأَسْمَاءِ",
        "Bab Idofah – بَابُ الإِضَافَةِ"
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
                        .width(180.dp)
                        .height(150.dp)
                        .align(Alignment.TopEnd) // Geser ke pojok kanan atas
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_lengkap),
                        contentDescription = "Rhymes Text",
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(y = 0.dp, x = 0.dp)
                            .fillMaxWidth()
                    )

                }
            }


// Gambar Orang + Teks
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth().offset(y = -60.dp)
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
                    .offset(y = -60.dp)
                    .drawBehind {
                        val shadowColorLight = Color.White.copy(alpha = 0.3f)
                        val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                        val cornerRadius = 50.dp.toPx()
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
                shape = RoundedCornerShape(50.dp),
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
                                val cornerRadius = 40.dp.toPx()
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
                        shape = RoundedCornerShape(50.dp),
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
                                shape = RoundedCornerShape(20.dp),
                                modifier = Modifier
                                    .width(with(LocalDensity.current) { dropdownWidth.toDp() })
                                    .background(Color(0xFFFFF5E0))
                                    .heightIn(max = 300.dp)
                            ) {
                                babList.forEach { bab ->
                                    DropdownMenuItem(
                                        modifier = Modifier.padding(vertical = 4.dp),
                                        text = {
                                            Box(
                                                modifier = Modifier.fillMaxWidth()
                                                    .background(Color.White, shape = RoundedCornerShape(10.dp))
                                                    .padding(4.dp),
                                                contentAlignment = Alignment.Center,
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
                    .padding(vertical = 16.dp)
                    .offset(y = -60.dp)
                    .drawBehind {
                        val shadowColorLight = Color.White.copy(alpha = 0.3f)
                        val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                        val cornerRadius = 50.dp.toPx()
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
                shape = RoundedCornerShape(50.dp),
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
                                onClick = {
                                    audioFilePath = recorder.startRecording()
                                    isRecording = true
                                },
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(Color.White, shape = CircleShape)
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.mic),
                                    contentDescription = "Mulai Rekam",
                                    modifier = Modifier.size(80.dp)
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                "Tekan mic untuk mulai merekam",
                                fontSize = 24.sp,
                                fontFamily = WinkySansFont,
                                color = Color(0xFF3A327C)
                            )
                        }
                    }

                    isRecording -> {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Gambar/tampilan status rekaman
                            GifTutupRekaman()

                            Spacer(modifier = Modifier.height(16.dp))

                            // Tombol play (tidak bisa ditekan)
                            IconButton(
                                onClick = { /* kosong, tidak aktif saat rekaman */ },
                                enabled = false
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.play),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                            }

                            // Tombol tutup rekaman = berhenti & simpan
                            IconButton(onClick = {
                                recorder.stopRecording()
                                isRecording = false
                                isRecordingStopped = true
                            }) {
                                Image(
                                    painter = painterResource(id = R.drawable.tutup_rekaman),
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                    }


                    isRecordingStopped -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            IconButton(onClick = {
                                if (isPlaying) {
                                    // Audio sedang diputar, jadi hentikan
                                    recorder.stopPlaying()
                                } else {
                                    // Audio tidak diputar, jadi mulai
                                    recorder.startPlaying(audioFilePath)
                                }
                                isPlaying = !isPlaying // Ubah status setelah aksi selesai
                            }) {
                                Image(
                                    painter = painterResource(id = if (isPlaying) R.drawable.play else R.drawable.stop),
                                    contentDescription = if (isPlaying) "Stop" else "Play",
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        }
                    }
                }}

                        // Tombol Kirim Hafalan
                        Button(
                            onClick = {
                                showPopup = true
                                isRecording = false
                                isRecordingStopped = false
                                isPlaying = false
                                recorder.stopPlaying()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6E502F)),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp)
                                .drawBehind {
                                    val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                    val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                    val cornerRadius = 25.dp.toPx()
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
                            shape = RoundedCornerShape(30.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.pesawat_kertas),
                                contentDescription = null,
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
                                containerColor = Color(0xFFFFF2DF),
                                modifier = Modifier
                                    .drawBehind {
                                    val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                    val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                    val cornerRadius = 30.dp.toPx()
                                    val blurRadius = 10.dp.toPx()

                                    // Shadow terang (atas kiri)
                                    drawRoundRect(
                                        color = shadowColorLight,
                                        topLeft = Offset(10f, 15f),
                                        size = size,
                                        cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                        blendMode = BlendMode.SrcOver
                                    )

                                    // Shadow gelap (bawah kanan)
                                    drawRoundRect(
                                        color = shadowColorDark,
                                        topLeft = Offset(6f, 9f),
                                        size = size,
                                        cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                        blendMode = BlendMode.SrcOver
                                    )
                                },
                                onDismissRequest = { showPopup = false },
                                confirmButton = {
                                    Button(onClick = { showPopup = false },
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color(0xFF6E502F)),
                                        modifier = Modifier
                                        .drawBehind {
                                            val shadowColorLight = Color.White.copy(alpha = 0.3f)
                                            val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                                            val cornerRadius = 20.dp.toPx()
                                            val blurRadius = 10.dp.toPx()

                                            // Shadow terang (atas kiri)
                                            drawRoundRect(
                                                color = shadowColorLight,
                                                topLeft = Offset(-5f, -5f),
                                                size = size,
                                                cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                                blendMode = BlendMode.SrcOver
                                            )

                                            // Shadow gelap (bawah kanan)
                                            drawRoundRect(
                                                color = shadowColorDark,
                                                topLeft = Offset(4f, 4f),
                                                size = size,
                                                cornerRadius = CornerRadius(cornerRadius, cornerRadius),
                                                blendMode = BlendMode.SrcOver
                                            )
                                        }) {
                                        Text("Oke",  fontSize = 22.sp,
                                            fontFamily = WinkySansFont)
                                    }
                                },
                                icon = {
                                    Image(
                                        painter = painterResource(id = R.drawable.berhasil),
                                        contentDescription = null
                                    )
                                },
                                title = { Text("Yeayy!!", fontSize = 30.sp,
                                    fontFamily = WinkySansFont, ) },
                                text = { Text("Hafalan berhasil dikirim!!", fontSize = 22.sp,
                                    fontFamily = WinkySansFont) }
                            )
                        }
                    }
                }
            }


