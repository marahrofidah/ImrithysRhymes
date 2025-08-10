package com.pida.imrithysrhymes

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.ImageLoader
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun GifTutupRekaman() {
    val context = LocalContext.current
    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (android.os.Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .build()
    AsyncImage(
        model = R.raw.sound, // file GIF di drawable/raw
        contentDescription = null,
        imageLoader = imageLoader,
        modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(30.dp))
    )
}
