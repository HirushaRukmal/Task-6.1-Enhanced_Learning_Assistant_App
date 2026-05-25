package com.example.enhancedlearningassistantapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.enhancedlearningassistantapp.R
import com.example.enhancedlearningassistantapp.data.DummyRepository
import com.example.enhancedlearningassistantapp.data.SessionManager
import com.example.enhancedlearningassistantapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        val session = SessionManager(requireContext())
        binding.textGreeting.text = "Hello,\n${session.getUsername()}"

        val task = DummyRepository.getGeneratedTask()
        binding.textTaskTitle.text = task.title
        binding.textTaskDescription.text = task.description

        binding.cardTask.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_taskFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}