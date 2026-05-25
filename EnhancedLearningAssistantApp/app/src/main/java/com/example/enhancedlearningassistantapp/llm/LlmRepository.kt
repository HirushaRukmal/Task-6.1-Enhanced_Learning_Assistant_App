package com.example.enhancedlearningassistantapp.llm

import com.example.enhancedlearningassistantapp.network.BackendApiService

class LlmRepository(private val api: BackendApiService = BackendApiService()) {
    suspend fun getHint(question: String) = api.generateHint(question)
    suspend fun getExplanation(question: String, correct: String, selected: String) = api.explainAnswer(question, correct, selected)
    suspend fun getSummary(topic: String) = api.createSummary(topic)
    suspend fun getFlashcards(topic: String) = api.createFlashcards(topic)
}
