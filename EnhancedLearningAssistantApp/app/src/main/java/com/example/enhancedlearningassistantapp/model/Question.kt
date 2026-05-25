package com.example.enhancedlearningassistantapp.model

data class Question(val questionText: String, val options: List<String>, val correctAnswerIndex: Int, var selectedIndex: Int = -1)
