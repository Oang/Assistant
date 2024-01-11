package com.philemon.olang.assistant.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GeminiOutputText(
    val output: String
)
