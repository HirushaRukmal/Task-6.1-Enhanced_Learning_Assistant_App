package com.example.enhancedlearningassistantapp.data

import com.example.enhancedlearningassistantapp.model.Interest
import com.example.enhancedlearningassistantapp.model.LearningTask
import com.example.enhancedlearningassistantapp.model.Question

object DummyRepository {
    fun getInterests(): List<Interest> = listOf(
        Interest("Algorithms"),
        Interest("Data Structures"),
        Interest("Web Development"),
        Interest("Testing"),
        Interest("Mobile Development"),
        Interest("Databases")
    )

    fun getGeneratedTask(): LearningTask = LearningTask(
        title = "Generated Task 1",
        description = "Small description for the generated task based on your selected interests.",
        questions = listOf(
            Question("What data structure uses FIFO order?", listOf("Stack", "Queue", "Tree", "Graph"), 1),
            Question("Which testing type checks the complete system behaviour?", listOf("Unit Testing", "System Testing", "Smoke Testing", "Mutation Testing"), 1),
            Question("Which HTML tag creates a hyperlink?", listOf("<a>", "<p>", "<div>", "<img>"), 0)
        )
    )
}
