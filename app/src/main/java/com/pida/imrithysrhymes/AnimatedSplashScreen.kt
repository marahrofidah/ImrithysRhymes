package com.pida.imrithysrhymes

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.*
//import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.pida.imrithysrhymes.ui.theme.SansationFont
import com.pida.imrithysrhymes.ui.theme.WinkySansFont



@Composable
fun AnimatedSplashScreen(onFinish: (String) -> Unit) {
    val scope = rememberCoroutineScope()
    val offsetYPerson = remember { Animatable(-500f) }
    val offsetYStar = remember { Animatable(-500f) }
    val offsetXText1 = remember { Animatable(-500f) } // Imrithy's dari kiri
    val offsetXText2 = remember { Animatable(500f) }  // Rhymes dari kanan
    val offsetYAllContent = remember { Animatable(0f) }
    var showInput by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    val interactionSource = remember { MutableInteractionSource() }
    val isHovered by interactionSource.collectIsHoveredAsState()


// Warna dinamis berdasarkan hover
    val buttonColor = if (isHovered) Color(0xFFFFC107) else Color(0xFF4C2882)
    val textColor = if (isHovered) Color(0xFF4C2882) else Color(0xFFFFC107)


    LaunchedEffect(true) {
        // Animasi masuk satu per satu
        offsetYPerson.animateTo(0f, animationSpec = tween(600, easing = FastOutSlowInEasing))
        delay(200)
        offsetYStar.animateTo(0f, animationSpec = tween(600, easing = FastOutSlowInEasing))
        delay(200)
        offsetXText1.animateTo(0f, animationSpec = tween(600, easing = FastOutSlowInEasing))
        delay(200)
        offsetXText2.animateTo(0f, animationSpec = tween(600, easing = FastOutSlowInEasing))
        delay(1000)
        offsetYAllContent.animateTo(-100f, tween(600)) // Semua naik ke atas
        delay(300)
        showInput = true
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
//        Column(horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier.padding(24.dp))
            // 1. Orng
            Image(
                painter = painterResource(id = R.drawable.person),
                contentDescription = "Person",
                modifier = Modifier
                    .offset(y = offsetYPerson.value.dp + offsetYAllContent.value.dp - 100.dp, x = 10.dp)
                    .align(Alignment.Center)
                    .size(150.dp)
            )

            // 2. Bintang
            Image(
                painter = painterResource(id = R.drawable.star),
                contentDescription = null,
                modifier = Modifier
                    .offset(y = offsetYStar.value.dp + offsetYAllContent.value.dp - 110.dp, x = -50.dp) // Naikin bintang
                    .align(Alignment.Center)
                    .size(120.dp)
            )

            // 3. Tulisan Imrithy’s
            Image(
                painter = painterResource(id = R.drawable.imrithys),
                contentDescription = "Imrithy's",
                modifier = Modifier
                    .offset(x = offsetXText1.value.dp - 10.dp, y = -20.dp + offsetYAllContent.value.dp ) // Geser ke bawah sedikit
                    .align(Alignment.Center)
                    .size(width = 400.dp, height = 110.dp)
            )

            // 4. Tulisan Rhymes
            Image(
                painter = painterResource(id = R.drawable.rhymes),
                contentDescription = "Rhymes",
                modifier = Modifier
                    .offset(x = offsetXText2.value.dp - -10.dp, y = 20.dp + offsetYAllContent.value.dp) // Geser lebih bawah
                    .align(Alignment.Center)
                    .size(width = 400.dp, height = 110.dp)
            )
        if (showInput) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = 50.dp), // Jarak dari tengah layar
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Username",
                    fontSize = 20.sp,
                    color = Color(0xFF3A327C),
                    fontFamily = WinkySansFont,
                    modifier = Modifier.padding(bottom = 8.dp)
                )


                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Username", fontFamily = WinkySansFont) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFFFC107),
                        unfocusedBorderColor = Color.LightGray,
                        focusedLabelColor = Color(0xFF3A327C),
                        unfocusedLabelColor = Color.Gray,
                        focusedTextColor = Color(0xFF3A327C),
                        unfocusedTextColor = Color.Gray

                    )
                )


                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (username.isNotBlank()) {
                            onFinish(username)
                    } },
                    colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
                    interactionSource = interactionSource,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 100.dp)
                ) {
                    Text(
                        text = "Next",
                        color = textColor,
                        fontFamily = WinkySansFont,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
        }
    }

