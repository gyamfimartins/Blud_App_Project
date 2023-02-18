package com.example.bludappproject.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.bludappproject.data.SharedPrefsWrapper
import com.example.bludappproject.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        val action = LoginFragmentDirections.actionGlobalSettingsFragment()
        binding.btngmail.setOnClickListener {
            findNavController().navigate(action)
            SharedPrefsWrapper.isLoggedIn = true
        }
        binding.btnguest.setOnClickListener {
            findNavController().navigate(action)
            SharedPrefsWrapper.isLoggedIn = true
        }

        return binding.root
    }

}