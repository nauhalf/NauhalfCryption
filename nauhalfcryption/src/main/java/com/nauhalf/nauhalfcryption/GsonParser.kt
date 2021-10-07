package com.nauhalf.nauhalfcryption

import com.google.gson.Gson
import java.lang.reflect.Type

class GsonParser(private val gson: Gson) : Parser {

    override fun <T : Any> fromJson(content: String?, type: Type?): T? {
        return if (content.isNullOrEmpty())
            null
        else
            gson.fromJson(content, type)
    }

    override fun toJson(body: Any?): String? {
        return gson.toJson(body)
    }
}