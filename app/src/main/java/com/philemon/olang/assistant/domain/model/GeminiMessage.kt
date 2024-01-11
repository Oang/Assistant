package com.philemon.olang.assistant.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class GeminiMessage(
    val content: String
)
