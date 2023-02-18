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
import com.bumptech.glide.Glide
import com.example.bludappproject.R
import com.example.bludappproject.data.SharedPrefsWrapper
import com.example.bludappproject.databinding.FragmentDashboardBinding
import com.example.bludappproject.utils.NUMBER_OF_PARTICIPANTS_DEFAULT
import com.example.bludappproject.utils.TYPE_RANDOM
import com.gyamfimartins.neverbored.model.BoredViewModel

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var viewModel: BoredViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(BoredViewModel::class.java)

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

        return binding.root
    }

    fun observeViewModel() {
        viewModel.boredData.observe(requireActivity(), Observer { boredData ->
            boredData?.let {
                if (!boredData.activity.isEmpty()) {
                    binding.ivloading.isVisible = false
                    binding.activityText.text = boredData.activity
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