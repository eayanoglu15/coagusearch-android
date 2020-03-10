package com.example.coagusearch.network.shared

import android.content.Context
import com.example.coagusearch.MainActivity

/**
 * Holds the application Android Context
 */
class ContextModule(
    private val applicationContext: Context
) {
    fun getContext(): Context {
        return applicationContext
    }

    fun getApplication(): MainActivity {
        return applicationContext.applicationContext as MainActivity
    }
}