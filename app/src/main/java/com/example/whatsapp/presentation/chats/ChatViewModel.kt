package com.example.whatsapp.presentation.chats

import androidx.lifecycle.ViewModel
import com.example.whatsapp.data.repository.chat.MessageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val messageRepository: MessageRepository
): ViewModel() {

    // Dummy messages
    private val _messages = MutableStateFlow(
        listOf(
            Message("1", "Salom Ali!", true),
            Message("2", "Salom! Qalesan?", false),
            Message("3", "Bugun uchrashamizmi?", false),
            Message("4", "Ha, bo‘ldi", true),
            Message("5", "Ok", false)
        )
    )

    val usermessage: StateFlow<List<Message>> = _messages

    // Flow orqali userId bo‘yicha xabarlarni olish
    fun getMessages(id: String): StateFlow<List<Message>> {
        return usermessage
    }

    // Xabar yuborish
    fun sendMessage(userId: String, text: String) {
        val newMessage = Message(
            userId = (_messages.value.size + 1).toString(),
            text = text,
            isMine = true
        )
        _messages.value = _messages.value + newMessage
    }


}
