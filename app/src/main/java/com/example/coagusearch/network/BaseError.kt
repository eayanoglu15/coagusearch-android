package com.example.coagusearch.network

import android.content.Context
import com.example.coagusearch.typing.Err

abstract class BaseError {

    protected abstract fun parseTitleAndMessage(context: Context): Pair<String, String>

    fun parseError(context: Context): Pair<String, String> =
        parseTitleAndMessage(context)

    fun toErr(): Err<BaseError> =
        Err(this)
}