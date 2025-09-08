package com.example.whatsapp.navigation


sealed class Screen(val route: String){
    object Auth: Screen("auth")
    object ProfileSetup: Screen("profile_setup")
    object Main: Screen("main")

    //Main ichidagi tablar
    object Home: Screen("home")
    object Profile: Screen("profile")



    object Chats: Screen("chat/{userId}/{userName}") {
        fun createRoute(userId: String, userName: String) = "chat/$userId/$userName"
    }



}