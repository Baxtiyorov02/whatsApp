package com.example.whatsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.whatsapp.presentation.auth.AuthScreen
import com.example.whatsapp.presentation.chats.ChatScreen
import com.example.whatsapp.presentation.main.MainScreen
import com.example.whatsapp.presentation.profile.setup.ProfileSetupScreen
@Composable
fun AppNavHost(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route
    ) {
        composable(Screen.Auth.route) {
            AuthScreen(
                onAuthSuccess = {
                    navController.navigate(Screen.ProfileSetup.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.ProfileSetup.route) {
            ProfileSetupScreen(
                onProfileSaved = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.ProfileSetup.route) { inclusive = true }
                    }
                }
            )
        }

        // MainScreen ichida BottomNavigation
        composable(Screen.Main.route) {
            MainScreen(navController)
        }

        // Chats screen
        composable(
            route = Screen.Chats.route,
            arguments = listOf(
                navArgument("userId") { type = NavType.StringType },
                navArgument("userName") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            val userName = backStackEntry.arguments?.getString("userName") ?: "Unknown"

            ChatScreen(
                navController = navController,
                userId = userId,
                userName = userName
            )
        }

    }
}
