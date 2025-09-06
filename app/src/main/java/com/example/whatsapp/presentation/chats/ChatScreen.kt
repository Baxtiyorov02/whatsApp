package com.example.whatsapp.presentation.chats

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.whatsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    userId: String,
    userName: String,
    onBack: () -> Unit
){
    Column {
        TopAppBar(
            title = { Text(userName) },
            navigationIcon = {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_back),
                        contentDescription = "Back"
                    )
                }
            }

        )
        Text("Chat with $userName (id: $userId)", Modifier.padding(16.dp))
    }

}