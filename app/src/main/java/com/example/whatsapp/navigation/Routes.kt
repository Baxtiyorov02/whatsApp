package com.example.whatsapp.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen{

    //Splash
    @Serializable
    object Splash: Screen()
    @Serializable
    object Auth: Screen()
    @Serializable
    object ProfileSetup: Screen()
    @Serializable
    object Main: Screen()

    //Main ichidagi tablar
    @Serializable
    object Home: Screen()
    @Serializable
    object Profile: Screen()


   /* object Chats: Screen("chat/{userId}/{userName}") {
        fun createRoute(userId: String, userName: String) =
            "chat/$userId/${userName}"
    }*/
   @Serializable
   data class Chats(val name: String,val id: String):Screen()

}