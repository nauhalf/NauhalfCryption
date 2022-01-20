package com.nauhalf.nauhalfcryptionsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.nauhalf.nauhalfcryption.NauhalfCryption

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NauhalfCryption.put("name", 10.5)
        Toast.makeText(this, NauhalfCryption.get<Double>("name").toString(), Toast.LENGTH_LONG).show()
        Log.d("walwaw", NauhalfCryption.get<String>("name").toString())
    }
}