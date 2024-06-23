package com.example.netflixrandom.ui.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes(val route:String, val icon: ImageVector, val label: String) {
    object HomeScreen: Routes("Home", Icons.Default.Home, "Home")
    object LastChoicesScreen: Routes("Movies", Icons.Default.ThumbUp, "Last")
}