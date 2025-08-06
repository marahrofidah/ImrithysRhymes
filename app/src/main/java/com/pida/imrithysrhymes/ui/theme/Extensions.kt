package com.pida.imrithysrhymes.ui.theme

//import androidx.compose.foundation.interaction.*
//import androidx.compose.runtime.*
//
//@Composable
//fun InteractionSource.collectIsHoveredAsState(): State<Boolean> {
//    val isHovered = remember { mutableStateOf(false) }
//    LaunchedEffect(this) {
//        interactions.collect { interaction ->
//            when (interaction) {
//                is HoverInteraction.Enter -> isHovered.value = true
//                is HoverInteraction.Exit -> isHovered.value = false
//            }
//        }
//    }
//    return isHovered
//}