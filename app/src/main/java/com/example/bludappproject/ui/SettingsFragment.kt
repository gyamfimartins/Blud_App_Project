package com.example.bludappproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bludappproject.R
import com.example.bludappproject.data.SharedPrefsWrapper
import com.example.bludappproject.databinding.FragmentSettingsBinding
import com.example.bludappproject.extensions.checkIfEditIsEmpty
import com.example.bludappproject.extensions.snack
import com.example.bludappproject.utils.*


class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private var isSelected = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        binding.settingsview.rginterest.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.random -> setPrefrence(TYPE_RANDOM)
                R.id.education -> setPrefrence(TYPE_EDUCATION)
                R.id.recreational -> setPrefrence(TYPE_RECREATIONAL)
                R.id.social -> setPrefrence(TYPE_SOCIAL)
                R.id.charity -> setPrefrence(TYPE_CHARITY)
                R.id.diy -> setPrefrence(TYPE_DIY)
                R.id.cooking -> setPrefrence(TYPE_COOKING)
                R.id.relaxation -> setPrefrence(TYPE_RELAXATION)
                R.id.music -> setPrefrence(TYPE_MUSIC)
                R.id.busywork -> setPrefrence(TYPE_BUSYWORK)
                R.id.participants -> showdialog()
                else -> {
                    setPrefrence(TYPE_RANDOM)
                }
            }
        }

        val action = SettingsFragmentDirections.actionGlobalDashboardFragment()

        binding.imgNext.setOnClickListener {
            if (binding.settingsview.editTextNickname.checkIfEditIsEmpty()) {
                SharedPrefsWrapper.nickname = binding.settingsview.editTextNickname.text.toString()
                findNavController().navigate(action)
            }
        }

        binding.imgFlairSoftTriangle.setOnClickListener {
            if (binding.settingsview.editTextNickname.checkIfEditIsEmpty()) {
                SharedPrefsWrapper.nickname = binding.settingsview.editTextNickname.text.toString()
                findNavController().navigate(action)
            }
        }

        SharedPrefsWrapper.nickname?.let {
            binding.settingsview.editTextNickname.setText(it)
        }

        checkCurrentActivity()

        return binding.root
    }

    private fun setPrefrence(interest: String, participants: String = "0") {
        SharedPrefsWrapper.interest = interest
        SharedPrefsWrapper.numberOfParticipants = participants
        view?.snack(SharedPrefsWrapper.interest!!)
    }

    private fun showdialog() {
        val items = arrayOf(getString(R.string.one), getString(R.string.two), getString(R.string.three), getString(
                    R.string.four), getString(R.string.five))
        val builder = AlertDialog.Builder(requireContext())
        with(builder)
        {
            setTitle(getString(R.string.num_title))
            setItems(items) { dialog, which ->
                setPrefrence(TYPE_PARTICIPANTS, items[which])
            }

            setNegativeButton(getString(R.string.cancel), { dialog, which -> dialog.cancel() })
            show()
        }
    }

    private fun checkCurrentActivity() {
        with(binding.settingsview) {
            when(SharedPrefsWrapper.interest) {
                TYPE_RANDOM -> rginterest.check(R.id.random)
                TYPE_EDUCATION -> rginterest.check(R.id.education)
                TYPE_RECREATIONAL -> rginterest.check(R.id.recreational)
                TYPE_SOCIAL -> rginterest.check(R.id.social)
                TYPE_CHARITY -> rginterest.check(R.id.charity)
                TYPE_DIY -> rginterest.check(R.id.diy)
                TYPE_COOKING -> rginterest.check(R.id.cooking)
                TYPE_RELAXATION -> rginterest.check(R.id.relaxation)
                TYPE_MUSIC -> rginterest.check(R.id.music)
                TYPE_BUSYWORK -> rginterest.check(R.id.busywork)
                TYPE_PARTICIPANTS -> rginterest.check(R.id.participants)
                else -> {
                    rginterest.check(R.id.random)
                }
            }
        }

    }

}