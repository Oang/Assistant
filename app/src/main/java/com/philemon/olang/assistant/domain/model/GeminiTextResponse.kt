package com.philemon.olang.assistant.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GeminiTextResponse(
    val candidates: List<GeminiOutputText>
)
