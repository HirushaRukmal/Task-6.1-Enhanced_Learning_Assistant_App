package com.example.enhancedlearningassistantapp.model

data class LlmUiState(val prompt: String = "", val response: String = "", val isLoading: Boolean = false, val error: String = "")
