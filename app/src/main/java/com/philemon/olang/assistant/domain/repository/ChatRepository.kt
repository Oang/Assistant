package com.philemon.olang.assistant.domain.repository

import android.net.Uri
import com.philemon.olang.assistant.domain.model.ChatWithMessages
import com.philemon.olang.assistant.domain.model.Message
import com.philemon.olang.assistant.domain.model.MessageSection
import com.philemon.olang.assistant.domain.model.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun sendPrompt(content: String, section: MessageSection, apiKey: String, pdfUri: Uri?): NetworkResult

    suspend fun sendMessage(newMessageContent: String, apiKey: String, chat: ChatWithMessages?, imageUri: Uri?, pdfUri: Uri?): NetworkResult

    fun getSectionChats(section: Int): Flow<List<ChatWithMessages>>

    suspend fun clearChatContext()

    suspend fun resetAllChats(section: Int)

    suspend fun writePdfFile(message: Message, uri: Uri): Boolean

}