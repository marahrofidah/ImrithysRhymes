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



@Composable
fun DengarkanSyairScreen(navController: NavHostController) {
    val playingIndex = remember { mutableStateOf(-1) }

    val daftarBab = listOf(
        "Pembukaan – المقدمة",
        "Bab Kalam – باب الكلام",
        "Bab I’rob – باب الإعراب",
        "Bab Alamat I’rob – بَابُ عَلَامَاتِ الإِعْرَابِ",
        "Bab Alamat Nashob – بَابُ عَلَامَاتِ النَّصْبِ",
        "Bab Alamat Jer – بَابُ عَلَامَاتِ الخَفْضِ",
        "Bab tanda-tanda Jazam – بَابُ عَلَامَاتِ الجَزْمِ"
    )

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
                imageRes = R.drawable.putarsemua,
                onClick = { /* aksi */ },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            RoundedImageButton(
                text = "Putar Acak",
                imageRes = R.drawable.acak,
                onClick = { /* aksi */ },
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
                BabButton(
                    text = item,
                    isPlaying = playingIndex.value == index,
                    onClick = { playingIndex.value = index }
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
            containerColor = Color(0xFFEDE9FB)
        ),
        modifier = modifier
            .height(155.dp)
            .padding(2.dp)
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
            fontWeight = FontWeight.Bold,
            fontSize = 21.sp,
            color = Color(0xFF3A327C)
        )
    }}
}


@Composable
fun BabButton(text: String, isPlaying: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(Color(0xFFDCDAFB))
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF3A327C),
            textAlign = TextAlign.Center
        )
        if (isPlaying) {
            Spacer(modifier = Modifier.height(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.play),
                contentDescription = "Play",
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.White, shape = CircleShape)
                    .padding(6.dp),
                tint = Color(0xFF3A327C)
            )
        }
    }
}

