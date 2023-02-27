package com.example.bludappproject.ui

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.bludappproject.R
import com.example.bludappproject.data.SharedPrefsWrapper
import com.example.bludappproject.databinding.FragmentDashboardBinding
import com.example.bludappproject.utils.NUMBER_OF_PARTICIPANTS_DEFAULT
import com.example.bludappproject.utils.TYPE_RANDOM
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.gyamfimartins.neverbored.model.BoredViewModel

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: BoredViewModel
    private var activityShown = ""
    private var activityId = ""
    private var db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(BoredViewModel::class.java)

        val account: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireActivity())

        if (account != null && !SharedPrefsWrapper.isLoggedIn) {
            SharedPrefsWrapper.uniqueId = account.email
        }

        val interest = SharedPrefsWrapper.interest ?: TYPE_RANDOM
        val numOfParticipants =
            SharedPrefsWrapper.numberOfParticipants ?: NUMBER_OF_PARTICIPANTS_DEFAULT

        viewModel.refresh(interest, numOfParticipants)
        observeViewModel()

        Glide.with(requireContext())
            .asGif()
            .load(R.drawable.loading)
            .into(binding.ivloading)

        binding.fbrefresh.setOnClickListener {
            viewModel.refresh(interest, numOfParticipants)
            observeViewModel()
        }



        binding.tvaccept.setOnClickListener {
            val activityMap = hashMapOf(
                "activityId" to activityId,
                "activity" to activityShown,
                "status" to "pending"
            )
            db.collection("savedactivity").document(SharedPrefsWrapper.uniqueId!!).set(activityMap)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Successfully Saved", Toast.LENGTH_LONG).show()
                }
        }

        binding.ivlist.setOnClickListener {
            val action = DashboardFragmentDirections.actionGlobalSavedActivitiesFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

    fun observeViewModel() {
        viewModel.boredData.observe(requireActivity(), Observer { boredData ->
            boredData?.let {
                if (!boredData.activity.isEmpty()) {
                    binding.ivloading.isVisible = false
                    binding.activityText.text = boredData.activity
                    activityShown = boredData.activity
                    activityId = boredData.key
                }

            }

        })
        viewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            Log.i(ContentValues.TAG, "isLoading $isLoading")
        })
        viewModel.errorMessage.observe(requireActivity(), Observer { errorMessage ->
            if (errorMessage != null) {
                Toast.makeText(requireActivity(), errorMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }
}