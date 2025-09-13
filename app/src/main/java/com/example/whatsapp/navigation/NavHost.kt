package com.example.whatsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.whatsapp.presentation.auth.AuthScreen
import com.example.whatsapp.presentation.chats.ChatScreen
import com.example.whatsapp.presentation.home.HomeScreen
import com.example.whatsapp.presentation.main.MainScreen
import com.example.whatsapp.presentation.profile.setup.ProfileSetupScreen
import com.example.whatsapp.presentation.splash.SplashScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Splash
    ) {



        //Splash
        composable<Screen.Splash> {
            println("Splash")
            SplashScreen(
                isLoggedInApp = {
                    navController.navigate(Screen.Main) {
                        popUpTo(Screen.Splash) { inclusive = true }
                    }
                },
                isNotLoggedInApp = {
                    navController.navigate(Screen.Auth) {
                        popUpTo(Screen.Splash) { inclusive = true }
                    }
                }
            )
        }
        composable<Screen.Auth> {
            println("Auth")
            AuthScreen(
                onAuthSuccess = {
                    navController.navigate(Screen.ProfileSetup) {
                        popUpTo(Screen.Auth) { inclusive = true }
                    }
                }
            )
        }

        composable<Screen.ProfileSetup> {
            println("ProfileSetup")
            ProfileSetupScreen(
                onProfileSaved = {
                    navController.navigate(Screen.Main) {
                        popUpTo(Screen.ProfileSetup) { inclusive = true }
                    }
                }
            )
        }

        // MainScreen ichida BottomNavigation
        composable<Screen.Main> {
            println("Main")
            MainScreen(popBackStack = {
                navController.popBackStack()
            },
                navToChat = { name, id ->
                    navController.navigate(Screen.Chats(name, id))
                })
        }

        composable<Screen.Home> {
            HomeScreen(popBackStack = {
                navController.popBackStack()
            },
                navToChat = { name, id ->
                    navController.navigate(Screen.Chats(name, id))
                })
        }

        composable<Screen.Chats>{
            val model = it.toRoute<Screen.Chats>()
            ChatScreen(
                userName =model.name,
                userId = model.id,
                popBackStack = {
                    navController.popBackStack()
                }
            )



        }

        // Chats screen
        /*composable(

            route = Screen.Chats.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            println("ChatScreen")
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userName = backStackEntry.arguments?.getString("userName") ?: "Unknown"

            ChatScreen(
                navController = navController,
                userId = userId,
                userName = userName
            )
        }*/

    }
}
