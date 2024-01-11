package com.philemon.olang.assistant.data.network

import com.philemon.olang.assistant.domain.model.GeminiMessagesResponse
import com.philemon.olang.assistant.domain.model.GeminiTextPrompt
import com.philemon.olang.assistant.domain.model.GeminiTextResponse
import com.philemon.olang.assistant.domain.model.GeminiMessagePrompt
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody


class GeminiApi(
    private val client: HttpClient
) {

    suspend fun generateMessage(
        prompt: GeminiMessagePrompt,
        apiKey: String
    ): GeminiMessagesResponse {
        return client.post(
            NetworkConstants.BASE_URL +
                    NetworkConstants.PALM_MESSAGES_MODEL +
                    apiKey
        ) {
            headers {
                append("Content-Type", "application/json")
            }
            setBody(prompt)
        }.body()
    }

    suspend fun generateText(
        prompt: GeminiTextPrompt,
        apiKey: String
    ): GeminiTextResponse {
        return client.post(
            NetworkConstants.BASE_URL +
                    NetworkConstants.PALM_TEXT_MODEL +
                    apiKey
        ) {
            headers {
                append("Content-Type", "application/json")
            }
            setBody(prompt)
        }.body()
    }

}