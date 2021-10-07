package com.nauhalf.nauhalfcryptionsample

import java.lang.reflect.Type

interface Parser {
    /**
     * Deserialize the given text for the given type and returns it.
     *
     * @param content is the value that will be deserialized.
     * @param type is the object type which value will be converted to.
     * @param <T> is the expected type.
     * @return the expected type.
     * @throws Exception if the operation is not successful.
    </T> */
    @Throws(Exception::class)
    fun <T : Any> fromJson(content: String?, type: Type?): T?

    /**
     * Serialize the given object to String.
     *
     * @param body is the object that will be serialized.
     * @return the serialized text.
     */
    fun toJson(body: Any?): String?
}