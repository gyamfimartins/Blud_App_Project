package com.example.bludappproject

import android.app.Application
import com.example.bludappproject.data.SharedPrefsWrapper

class BludApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPrefsWrapper.init(applicationContext)
    }
}
