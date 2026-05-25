package com.example.enhancedlearningassistantapp.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class BackendApiService {

    private val client = OkHttpClient()

    private val baseUrl = "http://10.0.2.2:3000"

    suspend fun generateHint(question: String): Pair<String, String> =
        withContext(Dispatchers.IO) {
            postJson("$baseUrl/llm/hint", JSONObject().put("question", question))
        }

    suspend fun explainAnswer(
        question: String,
        correctAnswer: String,
        selectedAnswer: String
    ): Pair<String, String> = withContext(Dispatchers.IO) {
        postJson(
            "$baseUrl/llm/explain",
            JSONObject()
                .put("question", question)
                .put("correctAnswer", correctAnswer)
                .put("selectedAnswer", selectedAnswer)
        )
    }

    suspend fun createSummary(topic: String): Pair<String, String> =
        withContext(Dispatchers.IO) {
            postJson("$baseUrl/llm/summary", JSONObject().put("topic", topic))
        }

    suspend fun createFlashcards(topic: String): Pair<String, String> =
        withContext(Dispatchers.IO) {
            postJson("$baseUrl/llm/flashcards", JSONObject().put("topic", topic))
        }

    private fun postJson(url: String, json: JSONObject): Pair<String, String> {
        val requestBody = json.toString()
            .toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            val body = response.body?.string().orEmpty()

            if (!response.isSuccessful) {
                val obj = if (body.isNotBlank()) JSONObject(body) else JSONObject()
                val error = obj.optString("error", "Unknown backend error")
                val fallback = obj.optString("fallback", "")

                return if (fallback.isNotBlank()) {
                    Pair("Backend fallback response", "$error\n\n$fallback")
                } else {
                    throw Exception(error)
                }
            }

            val result = JSONObject(body)
            val prompt = result.optString("prompt", "")
            val llmResponse = result.optString("response", "")
            return Pair(prompt, llmResponse)
        }
    }
}