package com.example.enhancedlearningassistantapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.enhancedlearningassistantapp.R
import com.example.enhancedlearningassistantapp.data.SessionManager
import com.example.enhancedlearningassistantapp.databinding.FragmentLoginBinding

class LoginFragment : Fragment(R.layout.fragment_login) {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentLoginBinding.bind(view)
        val sessionManager = SessionManager(requireContext())
        binding.buttonLogin.setOnClickListener {
            val username = binding.editUsername.text.toString().trim().ifEmpty { "Student" }
            sessionManager.saveUsername(username)
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        binding.textCreateAccount.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signupFragment)
        }
    }

    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
