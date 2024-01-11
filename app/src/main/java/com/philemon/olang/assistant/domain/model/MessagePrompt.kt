package com.philemon.olang.assistant.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class MessagePrompt(
    val context: String,
    val messages: List<GeminiMessage>
)
