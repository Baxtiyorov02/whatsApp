package com.example.whatsapp.navigation


sealed class Screen(val route: String){
    object Auth: Screen("auth")
    object Main: Screen("main")
    object Profile: Screen("profile")
    object Chat: Screen("chat/{userId}/{userName}"){
        fun createRoute(userId: String, userName: String) = "chat/$userId/$userName"

    }

}