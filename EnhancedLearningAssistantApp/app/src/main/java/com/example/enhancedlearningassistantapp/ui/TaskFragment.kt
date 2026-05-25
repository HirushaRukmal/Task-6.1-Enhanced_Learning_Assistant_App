package com.example.enhancedlearningassistantapp.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enhancedlearningassistantapp.R
import com.example.enhancedlearningassistantapp.data.LearningViewModel
import com.example.enhancedlearningassistantapp.databinding.FragmentTaskBinding

class TaskFragment : Fragment(R.layout.fragment_task) {
    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<LearningViewModel>()
    private var questionAdapter: QuestionAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentTaskBinding.bind(view)
        viewModel.task.observe(viewLifecycleOwner) { task ->
            binding.textTaskTitle.text = task.title
            binding.textTaskDescription.text = task.description
            if (questionAdapter == null) {
                questionAdapter = QuestionAdapter(task.questions)
                binding.recyclerQuestions.layoutManager = LinearLayoutManager(requireContext())
                binding.recyclerQuestions.adapter = questionAdapter
            }
            binding.buttonHint.setOnClickListener { viewModel.generateHint(task.questions.first().questionText) }
            binding.buttonSummary.setOnClickListener { viewModel.createSummary(task.title) }
            binding.buttonFlashcards.setOnClickListener { viewModel.createFlashcards(task.title) }
            binding.buttonSubmit.setOnClickListener {
                questionAdapter?.isSubmitted = true
                questionAdapter?.notifyDataSetChanged()
                val first = task.questions.first()
                val selected = if (first.selectedIndex >= 0) first.options[first.selectedIndex] else "No answer selected"
                val correct = first.options[first.correctAnswerIndex]
                viewModel.explainAnswer(first.questionText, correct, selected)
                val score = task.questions.count { it.selectedIndex == it.correctAnswerIndex }
                findNavController().navigate(R.id.action_taskFragment_to_resultFragment, bundleOf("score" to score, "total" to task.questions.size))
            }
        }
        viewModel.llmState.observe(viewLifecycleOwner) { state ->
            binding.progressLlm.visibility = if (state.isLoading) View.VISIBLE else View.GONE
            binding.textPrompt.text = state.prompt
            binding.textResponse.text = if (state.error.isNotEmpty()) state.error else state.response
        }
    }
    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
