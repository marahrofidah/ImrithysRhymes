package com.pida.imrithysrhymes


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pida.imrithysrhymes.ui.theme.WinkySansFont
import androidx.compose.material.icons.filled.Whatshot
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlin.system.exitProcess


data class MenuItem(val title: String, val iconRes: Int, val backgroundColor: Color)

@Composable
fun HomeScreen(username: String, navController: NavHostController, quizViewModel: QuizViewModel) {
    val currentProgress = quizViewModel.currentProgress
    val maxProgress = quizViewModel.maxProgress
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    top = 20.dp, // atur sendiri, misal 8.dp kalau masih mau jarak
                    bottom = innerPadding.calculateBottomPadding()
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                // 1. Logo "Imrithy’s Rhymes" di kanan atas (semua pakai gambar)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 4.dp, end = 4.dp),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .size(70.dp)
                            .clickable { /* Aksi info */ }
                    ) {
                        // Latar bulat info
                        Image(
                            painter = painterResource(id = R.drawable.lb_info),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                        // Ikon info di tengah
                        Image(
                            painter = painterResource(id = R.drawable.info), // ikon info
                            contentDescription = "Info",
                            modifier = Modifier
                                .size(42.dp) // sesuaikan ukuran ikon
                                .align(Alignment.Center)
                        )
                    }

                    // Kolom kiri: gambar person & bintang
//                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                        Image(
//                            painter = painterResource(id = R.drawable.ic_person),
//                            contentDescription = "Person Icon",
//                            modifier = Modifier.size(24.dp)
//                        )
//                        Image(
//                            painter = painterResource(id = R.drawable.star),
//                            contentDescription = "Star Icon",
//                            modifier = Modifier.size(16.dp)
//                        )
//                    }

                    // Kolom kanan: tulisan "Imrithy's" dan "Rhymes" (pakai gambar)
                    Box(
                        modifier = Modifier.height(180.dp) // cukup tinggi buat menumpuk
                    ) {
                        // Gambar atas
                        Image(
                            painter = painterResource(id = R.drawable.imrithys),
                            contentDescription = "Imrithys Text",
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .height(110.dp)
                                .offset(y = -30.dp, x = -20.dp)
                                .size(width = 129.dp, height = 150.dp)
                        )

                        // Gambar bawah (menumpuk dengan offset ke atas)
                        Image(
                            painter = painterResource(id = R.drawable.rhymes),
                            contentDescription = "Rhymes Text",
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .offset() // atur sejauh mana dia turun dari atas
                                .height(110.dp)
                                .size(width = 129.dp, height = 150.dp)
                        )
                    }
                }

                // 2. Card ucapan
                Card(
                    shape = RoundedCornerShape(40.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFC2E6FF)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 80.dp)

                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.person1),
                            contentDescription = "Orang",
                            modifier = Modifier.size(120.dp)
                        )
//                        Image(
//                            painter = painterResource(id = R.drawable.star),
//                            contentDescription = "bintang",
//                            modifier = Modifier
//                                .size(80.dp)
//                                .offset(x = -150.dp)
//                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Assalamu’alaikum, $username!",
                                fontSize = 20.sp,
                                fontFamily = WinkySansFont,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF3A327C)
                            )
                            Text(
                                text = "siap menaklukkan bait hari ini?",
                                fontSize = 16.sp,
                                fontFamily = WinkySansFont,
                                color = Color(0xFF3A327C)
                            )
                        }
                    }
                }
            }


                    // Tambahkan komponen lain seperti Focus Track, dll
            Card(
                shape = RoundedCornerShape(40.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE5A6)),
                elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                modifier = Modifier.fillMaxWidth()
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
                        progress = { currentProgress.toFloat() / maxProgress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                            .clip(RoundedCornerShape(100.dp)),
                        color = Color(0xFFFFC107),
                        trackColor = Color(0xFFE0E0E0)
                    )

                    Text(
                        text = "$currentProgress/$maxProgress",
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.align(Alignment.End)
                    )
                }

            }


            Spacer(modifier = Modifier.height(16.dp))

                    // Menu Tombol
                    val menuItems = listOf(
                        MenuItem("Dengarkan Syair", R.drawable.earbuds, Color(0xFFDCDAFB)),
                        MenuItem("Setor Hafalan", R.drawable.ic_person, Color(0xFFFFF2DF)),
                        MenuItem("Main Quiz", R.drawable.ic_timer, Color(0xFFDBFFD9)),
                        MenuItem("Buka Kitab", R.drawable.ic_book, Color(0xFFFFC9B7))
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.height(400.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),

                    )
                    {
                        items(items = menuItems) { item ->
                            MenuTile(item = item, navController = navController)
                        }

                    }
                    }
                }



    }


@Composable
fun MenuTile(item: MenuItem, navController: NavHostController) {
    Card(
        shape = RoundedCornerShape(40.dp),
        colors = CardDefaults.cardColors(containerColor = item.backgroundColor),
        modifier = Modifier
            .height(164.dp)
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
            }
            .fillMaxWidth()
                .clickable {
                    when (item.title) {
                        "Dengarkan Syair" -> navController.navigate("dengarkan_syair")
                        "Setor Hafalan" -> navController.navigate("setor_hafalan")
                        "Main Quiz" -> navController.navigate("main_quiz")

                        "Buka Kitab" -> navController.navigate("kitab")
                        else -> {
                            Log.e("NAV", "Menu tidak dikenali: ${item.title}")
                        }
                    }
                }
                ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()


        ) {
            Image(
                painter = painterResource(id = item.iconRes),
                contentDescription = item.title,
                modifier = Modifier.size(130.dp)
            )
//            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = item.title,
                fontFamily = WinkySansFont,
                color = Color(0xFF3A327C),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .offset(y = -10.dp),
                style = TextStyle(
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.25f),
                        offset = Offset(2f, 2f),
                        blurRadius = 2f
                    )

            )

            )
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val selectedIndex = remember { mutableStateOf(0) }
    val items = listOf("home", "book", "person", "exit")
    val icons = listOf(
        painterResource(id = R.drawable.l_home),
        painterResource(id = R.drawable.l_book),
        painterResource(id = R.drawable.l_user),
        painterResource(id = R.drawable.l_exit)
    )

    Surface(
        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
        color = Color(0xFF3A327C),
        tonalElevation = 20.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        NavigationBar(containerColor = Color.Transparent) {
            items.forEachIndexed { index, _ ->
                val selected = selectedIndex.value == index
                NavigationBarItem(
                    selected = selected,
                    onClick = { selectedIndex.value = index
                        when (items[index]) {
                            "home" -> navController.navigate("home")
                            "book" -> navController.navigate("kitab")
                            "person" -> navController.navigate("user")
                            "exit" -> exitProcess(0)
                        }
                    },
                    icon = {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(58.dp) // Ukuran container yang lebih besar untuk ngasih ruang
                        ) {
                            if (selected) {
                                Box(
                                    modifier = Modifier
                                        .size(52.dp) // Ukuran lingkaran background ini nahh ini blm bisa yang kibanya pindah tu
                                        .background(
                                            Color.White.copy(alpha = 0.3f),
                                            shape = CircleShape
                                        )
                                )
                            }
                            Image(
                                painter = icons[index],
                                contentDescription = items[index],
                                modifier = Modifier.size(38.dp) // Gedein ikon di sini
                            )
                        }
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFFFFC107),
                        unselectedIconColor = Color(0xFFFFC107),
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}




