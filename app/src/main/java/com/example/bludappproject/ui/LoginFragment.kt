package com.example.bludappproject.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.bludappproject.MainActivity
import com.example.bludappproject.data.SharedPrefsWrapper
import com.example.bludappproject.databinding.FragmentLoginBinding
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import java.text.SimpleDateFormat
import java.util.*


class LoginFragment : Fragment() {

    private val SIGN_IN_RESULT_CODE = 1001
    private lateinit var binding: FragmentLoginBinding
    private lateinit var action: NavDirections

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        action = LoginFragmentDirections.actionGlobalSettingsFragment()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val gsc = GoogleSignIn.getClient(requireActivity(),gso)

        //gsc.signOut().addOnSuccessListener {  }

        val account:GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireActivity())

        if (account != null || SharedPrefsWrapper.isLoggedIn) {
            findNavController().navigate(action)
        }

        binding.btngmail.setOnClickListener {
            val signInIntent = gsc.signInIntent
            activity?.startActivityForResult(signInIntent, SIGN_IN_RESULT_CODE)

        }
        binding.btnguest.setOnClickListener {
            findNavController().navigate(action)
            SharedPrefsWrapper.isLoggedIn = true
            SharedPrefsWrapper.uniqueId = getUniqueIdForGuestUsers()
        }

        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val result: GoogleSignInResult? = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)
            if (result != null && result.isSuccess) {
                findNavController().navigate(action)
            }

        }
    }

    fun getUniqueIdForGuestUsers(): String {
        val dNow = Date()
        val ft = SimpleDateFormat("yyMMddhhmmssMs")
        return ft.format(dNow)
    }

}