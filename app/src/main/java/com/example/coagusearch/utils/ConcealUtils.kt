package com.example.coagusearch.utils

import android.util.Base64
import com.facebook.crypto.Crypto
import com.facebook.crypto.Entity

fun Crypto.encrypt(key: String, value: String): String {
    val data = encrypt(value.toByteArray(Charsets.UTF_8), Entity.create(key))
    return Base64.encodeToString(data, Base64.DEFAULT)
}

fun Crypto.decrypt(key: String, value: String): String {
    val data = decrypt(Base64.decode(value, Base64.DEFAULT), Entity.create(key))
    return data.toString(Charsets.UTF_8)
}