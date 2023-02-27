package com.example.bludappproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.bludappproject.data.SharedPrefsWrapper
import com.example.bludappproject.databinding.FragmentSavedActivitiesBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.gyamfimartins.neverbored.model.SavedActivity

class SavedActivitiesFragment : Fragment() {
    private lateinit var binding: FragmentSavedActivitiesBinding
    private var db = Firebase.firestore
    private lateinit var savedActivities: ArrayList<SavedActivity>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSavedActivitiesBinding.inflate(inflater, container, false)

        val ref = db.collection("savedactivity").document(SharedPrefsWrapper.uniqueId!!)
        ref.get().addOnSuccessListener {
            if (it != null) {
                binding.tvactvity.setText(it.data?.get("activity").toString())
            }
        }

        return binding.root
    }

}