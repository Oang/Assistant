package com.philemon.olang.assistant.domain.model

import kotlinx.serialization.Serializable


@Serializable
data class GeminiMessagePrompt(
    val prompt: MessagePrompt,
    val candidate_count: Int = 1
)