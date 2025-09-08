package com.example.whatsapp.presentation.main

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.whatsapp.navigation.Screen
import com.example.whatsapp.presentation.home.HomeScreen
import com.example.whatsapp.presentation.profile.ProfileScreen

@Composable
fun MainScreen(
    rootNavController: NavHostController
){

    val navController= rememberNavController()


    Scaffold(
        bottomBar =  {
            NavigationBar {
                val items = listOf(Screen.Home, Screen.Profile)
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route


                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            when (screen) {
                                Screen.Home -> Icon(Icons.Default.Home, contentDescription = null)
                                Screen.Profile -> Icon(Icons.Default.Person, contentDescription = null)
                                else -> { }
                            }
                        },
                        label = { Text(screen.route) }, // istasang title qo‘shib olamiz
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }

                    )

                }
            }
        }
    ) {  paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)

        ){
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }

}