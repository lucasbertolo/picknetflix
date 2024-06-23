package com.example.netflixrandom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.netflixrandom.ui.composables.TabBarItem
import com.example.netflixrandom.ui.composables.TabView
import com.example.netflixrandom.ui.routes.Navigation
import com.example.netflixrandom.ui.routes.Routes
import com.example.netflixrandom.ui.theme.NetflixRandomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val homeTab = TabBarItem(
                title =  Routes.HomeScreen.route,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
            )
            val lastChoiceTab = TabBarItem(
                title = Routes.LastChoicesScreen.route,
                selectedIcon = Icons.Filled.ThumbUp,
                unselectedIcon = Icons.Outlined.ThumbUp
            )

            val tabBarItems = listOf(homeTab, lastChoiceTab)
            val navController = rememberNavController()

            NetflixRandomTheme {
                Scaffold(
                    bottomBar = {
                        TabView(
                            tabBarItems = tabBarItems,
                            navController = navController
                        )
                    },
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                ) { it ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(android.graphics.Color.parseColor("#082F4B")),
                                        Color(android.graphics.Color.parseColor("#064E34")),
                                    )
                                ),
                            )
                            .padding(it)
                    ) {
                        Navigation(navController)
                    }
                }
            }
        }
    }


}
