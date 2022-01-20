package com.nauhalf.nauhalfcryption

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE
import androidx.security.crypto.MasterKey.DEFAULT_MASTER_KEY_ALIAS
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class EncryptedSharedPrefs(context: Context) {


    // Since security-crypto 1.1.0-aplha01
    private val masterKey = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val spec = KeyGenParameterSpec.Builder(
            DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        ).setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .setKeySize(DEFAULT_AES_GCM_MASTER_KEY_SIZE)
            .build()

        MasterKey.Builder(context)
            .setKeyGenParameterSpec(spec)
            .build()
    } else {
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
    }

    val parser = GsonParser(Gson())

    val encPrefs = EncryptedSharedPreferences.create(
        context,
        "NauhalfCryption",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    inline fun <reified T> get(key: String, defaultValue: T?): T? {
        return get(key) ?: return defaultValue
    }

    inline fun <reified T> get(key: String): T? {
        if (!contains(key))
            return null
        return when (T::class) {
            String::class -> encPrefs.getString(key, "")
            Boolean::class -> encPrefs.getBoolean(key, false)
            Float::class -> encPrefs.getFloat(key, 0f)
            Double::class -> encPrefs.getDouble(key, 0.0)
            Int::class -> encPrefs.getInt(key, 0)
            Long::class -> encPrefs.getLong(key, 0L)
            else -> {
                val value = encPrefs.getString(key, "")
                val type = object : TypeToken<T?>() {}.type
                parser.fromJson(value, type)
            }
        } as T
    }

    fun <T> put(key: String, data: T?) {
        val editor = getEditor()
        if (data == null) {
            delete(key)
            return
        }
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putDouble(key, data)
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
            else -> {
                val json = parser.toJson(data)
                editor.putString(key, json)
            }
        }
        editor.apply()
    }

    fun delete(key: String?): Boolean {
        return getEditor().remove(key).commit()
    }

    fun deleteAll(): Boolean {
        return getEditor().clear().commit()
    }

    fun count(): Int {
        return encPrefs.all.size
    }

    fun contains(key: String?): Boolean {
        return encPrefs.contains(key)
    }

    private fun getEditor(): SharedPreferences.Editor {
        return encPrefs.edit()
    }
}