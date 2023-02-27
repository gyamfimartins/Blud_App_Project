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
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

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
            val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireActivity())
            if (account != null || SharedPrefsWrapper.isLoggedIn) {
                val actionToDashboard = GetStartedFragmentDirections.actionGlobalDashboardFragment()
                findNavController().navigate(actionToDashboard)
            }
        }

        return binding.root
    }
}