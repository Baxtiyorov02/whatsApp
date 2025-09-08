package com.example.whatsapp.presentation.chats

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.room.Entity
import com.example.whatsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    navController: NavHostController,
    userId: String,
    userName: String,
    viewModel: ChatViewModel = hiltViewModel()
) {
    val messages by viewModel.getMessages(userId).collectAsState(initial = emptyList())

    var currentMessage by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        // TopBar
        TopAppBar(
            title = { Text(userName) },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(painter = painterResource(id = R.drawable.ic_arrow_back), contentDescription = "Back")
                }
            }
        )

        // Xabarlar ro‘yxati
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(8.dp),
            reverseLayout = true
        ) {
            items(messages.reversed()) { msg ->
                MessageItem(message = msg)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // Xabar yozish qismi
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = currentMessage,
                onValueChange = { currentMessage = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Xabar yozing...") },
                singleLine = true
            )

            IconButton(
                onClick = {
                    if (currentMessage.isNotBlank()) {
                        viewModel.sendMessage(userId, currentMessage)
                        currentMessage = ""
                    }
                },
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFF25D366), CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = "Send",
                    tint = Color.White
                )
            }
        }
    }
}


@Composable
fun MessageItem(message: Message) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (message.isMine) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = if (message.isMine) Color(0xFFDCF8C6) else Color.LightGray,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(12.dp)
        ) {
            Text(text = message.text)
        }
    }
}
@Entity
data class Message(
    val id: String, val text: String, val isMine: Boolean)// true -> men yozgan xabar, false -> qarshi taraf
