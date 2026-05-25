package com.example.enhancedlearningassistantapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.enhancedlearningassistantapp.R
import com.example.enhancedlearningassistantapp.databinding.FragmentSignupBinding

class SignupFragment : Fragment(R.layout.fragment_signup) {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSignupBinding.bind(view)
        binding.buttonCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_interestsFragment)
        }
    }
    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
