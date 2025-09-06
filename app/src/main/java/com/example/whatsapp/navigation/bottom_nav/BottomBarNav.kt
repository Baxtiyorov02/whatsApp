package com.example.whatsapp.navigation.bottom_nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whatsapp.navigation.Screen
import com.example.whatsapp.presentation.main.MainScreen
import com.example.whatsapp.presentation.profile.ProfileScreen

@Composable
fun BottomBarNav(rootNavController: NavController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Main.route) },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Main") },
                    label = { Text("Main") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate(Screen.Profile.route) },
                    icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
                    label = { Text("Profile") }
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Main.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Main.route) {
                MainScreen(onUserClick = { id, name ->
                    rootNavController.navigate(Screen.Chat.createRoute(id, name))
                })
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}
