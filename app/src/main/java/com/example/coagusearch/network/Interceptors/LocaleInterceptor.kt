package com.example.coagusearch.network.Interceptors


import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response

class LocaleInterceptor(
    private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader("Accept-Language", context.getCurrentLocaleCode())
            .addHeader("X-Timezone", getCurrentTimezone())
            .addHeader("X-OS", "Android")
            .build()
        return chain.proceed(request)
    }
}