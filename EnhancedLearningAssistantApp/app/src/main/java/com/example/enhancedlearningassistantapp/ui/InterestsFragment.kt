package com.example.enhancedlearningassistantapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.enhancedlearningassistantapp.R
import com.example.enhancedlearningassistantapp.data.DummyRepository
import com.example.enhancedlearningassistantapp.data.SessionManager
import com.example.enhancedlearningassistantapp.databinding.FragmentInterestsBinding

class InterestsFragment : Fragment(R.layout.fragment_interests) {
    private var _binding: FragmentInterestsBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentInterestsBinding.bind(view)
        val interests = DummyRepository.getInterests()
        val chips = listOf(binding.chip1,binding.chip2,binding.chip3,binding.chip4,binding.chip5,binding.chip6)
        chips.forEachIndexed { idx, chip ->
            chip.text = interests[idx].name
            chip.setOnClickListener { chip.isSelected = !chip.isSelected }
        }
        binding.buttonNext.setOnClickListener {
            val selected = chips.filter { it.isSelected }.map { it.text.toString() }.toSet()
            SessionManager(requireContext()).saveInterests(selected)
            findNavController().navigate(R.id.action_interestsFragment_to_homeFragment)
        }
    }
    override fun onDestroyView() { super.onDestroyView(); _binding = null }
}
