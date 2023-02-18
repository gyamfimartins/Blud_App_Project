package com.example.bludappproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bludappproject.data.SharedPrefsWrapper
import com.example.bludappproject.databinding.FragmentGetStartedBinding
import com.example.bludappproject.extensions.animateViewWithFade

class GetStartedFragment : Fragment() {
    private lateinit var binding: FragmentGetStartedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetStartedBinding.inflate(inflater, container, false)

        with(binding) {
            buttonGetStarted.setOnClickListener {
                val actionToLogin = GetStartedFragmentDirections.actionGlobalLoginFragment()
                findNavController().navigate(actionToLogin)
            }

            buttonGetStarted.animateViewWithFade(requireContext())

            if (SharedPrefsWrapper.isLoggedIn) {
                val actionToDashboard = GetStartedFragmentDirections.actionGlobalDashboardFragment()
                findNavController().navigate(actionToDashboard)
            }
        }

        return binding.root
    }
}