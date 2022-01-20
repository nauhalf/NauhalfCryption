package com.nauhalf.nauhalfcryption

import android.content.Context

object NauhalfCryption {

    var encSharedPrefs: EncryptedSharedPrefs? = null
    const val NOT_INIT_MESSAGE =
        "NauhalfCryption is not initialized yet. Please call NauhalfCryption.init(application) in App module"

    fun initSingleton(context: Context) {
        encSharedPrefs = null
        encSharedPrefs = EncryptedSharedPrefs(context)
    }

    fun build(context: Context): EncryptedSharedPrefs{
        return EncryptedSharedPrefs(context)
    }

    fun destroy() {
        encSharedPrefs = null
    }

    inline fun <reified T> get(key: String, defaultValue: T?): T? {
        return requireNotNull(encSharedPrefs) {
            NOT_INIT_MESSAGE
        }.get(key, defaultValue)
    }

    inline fun <reified T> get(key: String): T? {
        return requireNotNull(encSharedPrefs) {
            NOT_INIT_MESSAGE
        }.get(key)
    }

    fun <T> put(key: String, data: T?) {
        requireNotNull(encSharedPrefs) {
            NOT_INIT_MESSAGE
        }.put(key, data)
    }

    fun delete(key: String?): Boolean {
        return requireNotNull(encSharedPrefs) {
            NOT_INIT_MESSAGE
        }.delete(key)
    }

    fun deleteAll(): Boolean {
        return requireNotNull(encSharedPrefs) {
            NOT_INIT_MESSAGE
        }.deleteAll()
    }

    fun count(): Int {
        return requireNotNull(encSharedPrefs) {
            NOT_INIT_MESSAGE
        }.count()
    }

    fun contains(key: String?): Boolean {
        return requireNotNull(encSharedPrefs) {
            NOT_INIT_MESSAGE
        }.contains(key)
    }
}
