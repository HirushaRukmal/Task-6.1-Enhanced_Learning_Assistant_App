package com.example.enhancedlearningassistantapp.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.enhancedlearningassistantapp.R
import com.example.enhancedlearningassistantapp.data.LearningViewModel
import com.example.enhancedlearningassistantapp.databinding.FragmentResultBinding

class ResultFragment : Fragment(R.layout.fragment_result) {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModels<LearningViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentResultBinding.bind(view)

        val score = arguments?.getInt("score", 0) ?: 0
        val total = arguments?.getInt("total", 0) ?: 0
        val percentage = if (total > 0) (score * 100) / total else 0

        viewModel.task.observe(viewLifecycleOwner) { task ->
            val resultText = StringBuilder().apply {
                append("Total Score: $score / $total\n")
                append("Percentage: $percentage%\n\n")

                task.questions.forEachIndexed { index, q ->
                    val selectedAnswer =
                        if (q.selectedIndex >= 0) q.options[q.selectedIndex] else "Not answered"
                    val correctAnswer = q.options[q.correctAnswerIndex]
                    val status =
                        if (q.selectedIndex == q.correctAnswerIndex) "Correct" else "Wrong"

                    append("Question ${index + 1}: ${q.questionText}\n")
                    append("Selected Answer: $selectedAnswer\n")
                    append("Correct Answer: $correctAnswer\n")
                    append("Result: $status\n\n")
                }
            }.toString()

            binding.textResults.text = resultText

            if (percentage >= 50) {
                binding.textScoreCard.setTextColor(Color.parseColor("#2E7D32"))
            } else {
                binding.textScoreCard.setTextColor(Color.parseColor("#C62828"))
            }

            binding.textScoreCard.text = "Total Score: $score / $total"
            binding.textPercentage.text = "Percentage: $percentage%"
        }

        binding.buttonContinue.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}