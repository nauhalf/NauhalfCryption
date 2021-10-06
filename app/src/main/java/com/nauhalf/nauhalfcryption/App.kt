package com.nauhalf.nauhalfcryption

import android.app.Application
import com.nauhalf.nauhalcrytion.NauhalfCryption

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        NauhalfCryption.init(this)
    }

}