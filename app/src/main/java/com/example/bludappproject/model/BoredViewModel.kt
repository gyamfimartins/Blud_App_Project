package com.gyamfimartins.neverbored.model

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bludappproject.utils.TYPE_PARTICIPANTS
import com.example.bludappproject.utils.TYPE_RANDOM
import com.gyamfimartins.neverbored.network.BoredService
import kotlinx.coroutines.launch

class BoredViewModel: ViewModel() {
    private val _boredData: MutableLiveData<Bored> = MutableLiveData()
    val boredData: LiveData<Bored>
        get() = _boredData

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?>
        get() = _errorMessage

    fun refresh(type: String, participants: String) {
        if (type.equals(TYPE_RANDOM)) {
            getRandom()
        }
        else if (type.equals(TYPE_PARTICIPANTS)) {
            getParticipants(participants)
        }
        else {
            getActivity(type)
        }

    }

    fun getRandom() {
        viewModelScope.launch {
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedData = BoredService().getBoredService().getRandomActivity()
                Log.i(ContentValues.TAG, "Activity: $fetchedData")
                _boredData.value = fetchedData
            } catch (e: Exception) {
                _errorMessage.value = e.message
                Log.e(ContentValues.TAG, "Exception $e")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getActivity(type: String) {
        viewModelScope.launch {
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedData = BoredService().getBoredService().getByActivityType(type)
                Log.i(ContentValues.TAG, "Activity: $fetchedData")
                _boredData.value = fetchedData
            } catch (e: Exception) {
                _errorMessage.value = e.message
                Log.e(ContentValues.TAG, "Exception $e")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getParticipants(participants: String) {
        viewModelScope.launch {
            _errorMessage.value = null
            _isLoading.value = true
            try {
                val fetchedData = BoredService().getBoredService().getByParticipants(participants)
                Log.i(ContentValues.TAG, "Activity: $fetchedData")
                _boredData.value = fetchedData
            } catch (e: Exception) {
                _errorMessage.value = e.message
                Log.e(ContentValues.TAG, "Exception $e")
            } finally {
                _isLoading.value = false
            }
        }
    }

}