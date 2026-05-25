package com.example.enhancedlearningassistantapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enhancedlearningassistantapp.llm.LlmRepository
import com.example.enhancedlearningassistantapp.model.LearningTask
import com.example.enhancedlearningassistantapp.model.LlmUiState
import kotlinx.coroutines.launch

class LearningViewModel : ViewModel() {
    private val llmRepository = LlmRepository()
    private val _task = MutableLiveData(DummyRepository.getGeneratedTask())
    val task: LiveData<LearningTask> = _task
    private val _llmState = MutableLiveData(LlmUiState())
    val llmState: LiveData<LlmUiState> = _llmState

    fun generateHint(question: String) {
        _llmState.value = LlmUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val result = llmRepository.getHint(question)
                _llmState.value = LlmUiState(prompt = result.first, response = result.second)
            } catch (e: Exception) {
                _llmState.value = LlmUiState(error = "Failed to generate hint: ${e.message}")
            }
        }
    }

    fun explainAnswer(question: String, correct: String, selected: String) {
        _llmState.value = LlmUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val result = llmRepository.getExplanation(question, correct, selected)
                _llmState.value = LlmUiState(prompt = result.first, response = result.second)
            } catch (e: Exception) {
                _llmState.value = LlmUiState(error = "Failed to explain answer: ${e.message}")
            }
        }
    }

    fun createSummary(topic: String) {
        _llmState.value = LlmUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val result = llmRepository.getSummary(topic)
                _llmState.value = LlmUiState(prompt = result.first, response = result.second)
            } catch (e: Exception) {
                _llmState.value = LlmUiState(error = "Failed to create summary: ${e.message}")
            }
        }
    }

    fun createFlashcards(topic: String) {
        _llmState.value = LlmUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val result = llmRepository.getFlashcards(topic)
                _llmState.value = LlmUiState(prompt = result.first, response = result.second)
            } catch (e: Exception) {
                _llmState.value = LlmUiState(error = "Failed to create flashcards: ${e.message}")
            }
        }
    }
}
