package com.example.whatsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.whatsapp.navigation.bottom_nav.BottomBarNav
import com.example.whatsapp.presentation.auth.AuthScreen
import com.example.whatsapp.presentation.chats.ChatScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Auth.route
    ) {
        composable(Screen.Auth.route) {
            AuthScreen(
                onSendCode = { phoneNumber ->
                    navController.navigate(Screen.Main.route){
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                },
                onVerifyCode = { otpCode ->
                    navController.navigate(Screen.Main.route){
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Main.route){
            BottomBarNav(navController)

        }

        composable(Screen.Chat.route){ backStaceEntry ->
            val userId = backStaceEntry.arguments?.getString("userId") ?:""
            val userName = backStaceEntry.arguments?.getString("userName") ?:""
            ChatScreen(userId = userId, userName = userName){
                navController.popBackStack()
            }


        }

    }

}