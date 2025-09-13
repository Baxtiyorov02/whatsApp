package com.example.whatsapp.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.whatsapp.presentation.home.HomeScreen
import com.example.whatsapp.presentation.profile.ProfileScreen

@Composable
fun BottomNavHost(
    modifier: Modifier = Modifier,
    selectedItems: Int,
    popBackStack: () -> Unit,
    navToChat:(String, String)-> Unit,
) {
    when (selectedItems) {
        0 -> HomeScreen(
            popBackStack = {
                popBackStack.invoke()
            },
            navToChat = {name,id->
                navToChat.invoke(name,id) })

        1 -> ProfileScreen()
    }
}