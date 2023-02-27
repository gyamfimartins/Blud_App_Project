package com.example.bludappproject.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.example.bludappproject.utils.NUMBER_OF_PARTICIPANTS_DEFAULT
import com.example.bludappproject.utils.TYPE_RANDOM


private const val KEY_SHARED_PREF = "blud_shared_pref"
private const val KEY_NICKNAME = "nickname_key"
private const val KEY_LOGIN_STATUS = "login_status"
private const val KEY_INTEREST = "interest"
private const val KEY_NUMBER_OF_PARTICIPANTS = "number_of_participants"
private const val KEY_UNIQUE_ID = "unique_id"

/**
 * Singleton wrapper to access Shared Prefs.
 */
object SharedPrefsWrapper {
    private lateinit var sharedPrefs: SharedPreferences

    fun init(context: Context) {
        sharedPrefs = context.getSharedPreferences(KEY_SHARED_PREF, Context.MODE_PRIVATE)
    }

    var isLoggedIn: Boolean
        get() = sharedPrefs.getBoolean(KEY_LOGIN_STATUS, false)
        set(value) = sharedPrefs.edit { putBoolean(KEY_LOGIN_STATUS, value) }

    var nickname: String?
        get() = sharedPrefs.getString(KEY_NICKNAME, null)
        set(value) = sharedPrefs.edit { putString(KEY_NICKNAME, value) }

    var interest: String?
        get() = sharedPrefs.getString(KEY_INTEREST, TYPE_RANDOM)
        set(value) = sharedPrefs.edit { putString(KEY_INTEREST, value) }

    var numberOfParticipants: String?
        get() = sharedPrefs.getString(KEY_NUMBER_OF_PARTICIPANTS, NUMBER_OF_PARTICIPANTS_DEFAULT)
        set(value) = sharedPrefs.edit { putString(KEY_NUMBER_OF_PARTICIPANTS, value) }

    var uniqueId: String?
        get() = sharedPrefs.getString(KEY_UNIQUE_ID, null)
        set(value) = sharedPrefs.edit { putString(KEY_UNIQUE_ID, value) }

}