package com.nauhalf.nauhalfcryption

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.nauhalf.nauhalcrytion.NauhalfCryption

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NauhalfCryption.put("name", "dzakdzaks")
        Toast.makeText(this, NauhalfCryption.get<String>("name").toString(), Toast.LENGTH_LONG).show()
        Log.d("walwaw", NauhalfCryption.get<String>("name").toString())
    }
}