package com.philemon.olang.assistant.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class GeminiMessagesResponse(
    val candidates: List<GeminiMessage>
)