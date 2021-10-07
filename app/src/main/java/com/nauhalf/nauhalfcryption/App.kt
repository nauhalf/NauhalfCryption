package com.nauhalf.nauhalfcryption

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        NauhalfCryption.init(this)
    }

}