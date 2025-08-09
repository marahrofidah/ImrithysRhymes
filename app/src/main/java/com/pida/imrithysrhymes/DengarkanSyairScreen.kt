package com.pida.imrithysrhymes

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon

import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import android.media.MediaPlayer
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.platform.LocalContext
import com.pida.imrithysrhymes.ui.theme.WinkySansFont


@Composable
fun DengarkanSyairScreen(navController: NavHostController) {
    val playingIndex = remember { mutableStateOf(-1) }
    val context = LocalContext.current
    val mediaPlayer = remember { mutableStateOf<MediaPlayer?>(null) }
    val expandedIndex = remember { mutableStateOf(-1) } // buat buka-tutup kolom
    val isAudioPlaying = remember { mutableStateOf(false) } // buat toggle play/stop
    val isPlayingAll = remember { mutableStateOf(false) }
    val isShuffle = remember { mutableStateOf(false) }



    val daftarBab = listOf(
        "Pembukaan – المقدمة" to R.raw.pembukaan,
        "Bab Kalam – باب الكلام" to R.raw.kalam,
        "Bab I’rob – باب الإعراب" to R.raw.irab,
        "Bab Alamat I’rob – بَابُ عَلَامَاتِ الإِعْرَابِ" to null,
        "Bab Alamat Nashob – بَابُ عَلَامَاتِ النَّصْبِ" to null,
        "Bab Alamat Jer – بَابُ عَلَامَاتِ الخَفْضِ" to null,
        "Bab tanda-tanda Jazam – بَابُ عَلَامَاتِ الجَزْمِ" to null,
        "Fasal – فَصْلٌ" to null,
        "Bab Makrifat dan Nakirah – بَابُ المَعْرِفَةِ وَالنَّكِرةِ" to null,
        "Bab Fiil-fiil – بَابُ الأَفْعَالِ" to null,
        "Bab I’rab Fiil – بَابُ إِعْرَابِ الْفِعْلِ" to null,
        "Bab isim-isim yang dibaca Rafa – بَابُ مَرْفَوعَاتِ الأَسْمَاءِ" to null,
        "Bab Naibul Fail – بَابُ نَائِبِ الْفَاعِلِ" to null,
        "Bab Mubtada dan Khobar – بَابُ المُبْتَدَإ وَالْخَبَر" to null,
        "Kana dan Saudar-saudaranya – كان وأخواتها" to null,
        "Inna dan saudara-saudaranya – إن وأخواتها" to null,
        "Dzanna dan saudara-saudaranya – ظن وأخواتها" to null,
        "Bab Na'at – بَابُ النَّعْتِ" to null,
        "Bab Ataf – بَابُ الْعَطْفِ" to null,
        "Bab Taukid – بَابُ التَّوكِيدِ" to null,
        "Bab Badal – بَابُ الْبَدَلِ" to null,
        "Bab isim-isim yang dibaca nashob – بَابُ مَنْصُوبَاتِ الأَسْمَاءِ" to null,
        "Bab Masdar – بَابُ المَصْدَرِ" to null,
        "Bab Dhorof – بَاب الظَرْفِ" to null,
        "Bab Hal – بَابُ الحَالِ" to null,
        "Bab Tamyiz – بَابُ التَّمْيِيزِ" to null,
        "Bab Istisna – بَابُ الاُسْتِثْنَاءِ" to null,
        "Bab la  yang beramal seperti amal inna – بَابُ لاَ الْعَامِلَةِ عَمَلَ إِنَّ" to null,
        "Bab Munada – بَابُ النِّدَاءِ" to null,
        "Bab Maful Li ajlih – بَابُ المَفْعُولِ لأَجْلِهِ" to null,
        "Bab Maful Maah – بَابُ المَفْعُولِ مَعَهُ" to null,
        "Bab isim-isim yang dibaca jer – بَابُ مَخْفوضَاتِ الأَسْمَاءِ" to null,
        "Bab Idofah – بَابُ الإِضَافَةِ" to null



    )
    fun playAllAudios(list: List<Pair<String, Int>>, index: Int = 0) {
        if (index >= list.size) {
            // Selesai putar semua
            isPlayingAll.value = false
            playingIndex.value = -1
            mediaPlayer.value?.release()
            mediaPlayer.value = null
            return
        }

        val resId = list[index].second
        val player = MediaPlayer.create(context, resId)

        mediaPlayer.value?.stop()
        mediaPlayer.value?.release()
        mediaPlayer.value = player

        playingIndex.value = daftarBab.indexOfFirst { it.second == resId }

        player.setOnCompletionListener {
            mediaPlayer.value = null
            playAllAudios(list, index + 1)
        }


        player.start()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // 🔹 HEADER (ikon + judul + logo)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.offset(x = 0.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.earbuds),
                    contentDescription = "Icon Headphone",
                    modifier = Modifier.size(100.dp)
                )

                 // jarak atas ke teks

                Column {
                    Text(
                        text = "Dengarkan Syair",
                        fontSize = 35.sp,
                        fontFamily = WinkySansFont,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF3A327C)
                    )}
            }

            Box(
                modifier = Modifier
                    .width(129.dp)
                    .height(150.dp)
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

        // 🔹 TOMBOL PUTAR SEMUA & ACAK
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .offset(y = -20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RoundedImageButton(
                text = "Putar Semua",
                imageRes = if (isPlayingAll.value && !isShuffle.value) R.drawable.play else R.drawable.putarsemua,
                onClick = {
                    if (isPlayingAll.value && !isShuffle.value) {
                        // Stop
                        mediaPlayer.value?.stop()
                        mediaPlayer.value?.release()
                        mediaPlayer.value = null
                        playingIndex.value = -1
                        isPlayingAll.value = false
                    } else {
                        // Play semua urut
                        val orderedList = daftarBab.filter { it.second != null }
                            .map { Pair(it.first, it.second!!) }
                        isShuffle.value = false
                        isPlayingAll.value = true
                        playAllAudios(orderedList)
                    }
                },
                modifier = Modifier.weight(1f)
            )

            Spacer(modifier = Modifier.width(16.dp))

            RoundedImageButton(
                text = "Putar Acak",
                imageRes = if (isPlayingAll.value && isShuffle.value) R.drawable.play else R.drawable.acak,
                onClick = {
                    if (isPlayingAll.value && isShuffle.value) {
                        // Stop
                        mediaPlayer.value?.stop()
                        mediaPlayer.value?.release()
                        mediaPlayer.value = null
                        playingIndex.value = -1
                        isPlayingAll.value = false
                    } else {
                        // Play semua acak
                        val shuffledList = daftarBab.filter { it.second != null }
                            .map { Pair(it.first, it.second!!) }
                            .shuffled()
                        isShuffle.value = true
                        isPlayingAll.value = true
                        playAllAudios(shuffledList)
                    }
                },
                modifier = Modifier.weight(1f)
            )

        }

//        Spacer(modifier = Modifier.height(-10.dp))

        // 🔹 DAFTAR SYAIR
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
                .offset(y = -10.dp)

        ) {
            itemsIndexed(daftarBab) { index, item ->
                val isThisExpanded = expandedIndex.value == index
                val isThisPlaying = playingIndex.value == index
                val audioRes = item.second

                BabButton(
                    text = item.first,
                    isExpanded = isThisExpanded,
                    isAudioPlaying = isThisPlaying,
                    onMainClick = {
                        // klik pertama: buka/tutup tombol
                        if (isThisExpanded) {
                            expandedIndex.value = -1
                        } else {
                            expandedIndex.value = index
                            // reset audio state
                            playingIndex.value = -1
                            mediaPlayer.value?.stop()
                            mediaPlayer.value?.release()
                            mediaPlayer.value = null
                        }
                    },
                    onIconClick = {
                        if (isThisPlaying) {
                            // sedang main -> stop
                            mediaPlayer.value?.pause()
                            mediaPlayer.value?.seekTo(0)
                            mediaPlayer.value?.release()
                            mediaPlayer.value = null
                            playingIndex.value = -1
                        } else {
                            audioRes?.let {
                                mediaPlayer.value?.stop()
                                mediaPlayer.value?.release()
                                val player = MediaPlayer.create(context, it)
                                player.setOnCompletionListener {
                                    playingIndex.value = -1
                                    mediaPlayer.value = null
                                }
                                player.start()
                                mediaPlayer.value = player
                                playingIndex.value = index
                            }
                        }
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
            }

        }

        // 🔹 BOTTOM NAV
        BottomNavigationBar(navController = navController)
    }
}

@Composable
fun RoundedImageButton(
    text: String,
    imageRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFDCDAFB)
        ),
        modifier = modifier
            .height(155.dp)
            .padding(2.dp)
            .drawBehind {
                val shadowColorLight = Color.White.copy(alpha = 0.3f)
                val shadowColorDark = Color.Black.copy(alpha = 0.3f)
                val cornerRadius = 20.dp.toPx()
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
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth().fillMaxHeight()
        ) {
        Icon(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            tint = Color(0xFF3A327C)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text,
            fontFamily = WinkySansFont,
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
            color = Color(0xFF3A327C)
        )
    }}
}


@Composable
fun BabButton(
    text: String,
    isExpanded: Boolean,
    isAudioPlaying: Boolean,
    onMainClick: () -> Unit,
    onIconClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFFDCDAFB))
            .clickable { onMainClick() }
            .padding(vertical = 12.dp, horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontFamily = WinkySansFont,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF3A327C),
            textAlign = TextAlign.Center
        )

        if (isExpanded) {
            Spacer(modifier = Modifier.height(6.dp))
            Icon(
                painter = painterResource(
                    id = if (isAudioPlaying) R.drawable.play else R.drawable.stop
                ),
                contentDescription = if (isAudioPlaying) "Pause" else "Play",
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.White, shape = CircleShape)
                    .padding(6.dp)
                    .clickable { onIconClick() },
                tint = Color(0xFF3A327C)
            )
        }
    }
}

