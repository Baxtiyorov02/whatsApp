package com.example.whatsapp.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import javax.inject.Singleton

@Composable
@Singleton
fun MainScreen(
    onUserClick: (String, String) -> Unit
) {
    val users = listOf("Ali", "Vali", "Salim")
    LazyColumn {
        items(users) { name ->
            Text(
                text = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onUserClick(name.lowercase(), name) }
                    .padding(16.dp)
            )
        }
    }

}