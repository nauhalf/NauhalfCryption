package com.nauhalf.nauhalfcryptionsample

import android.app.Application
import com.nauhalf.nauhalfcryption.NauhalfCryption

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        NauhalfCryption.init(this.applicationContext)
    }

}