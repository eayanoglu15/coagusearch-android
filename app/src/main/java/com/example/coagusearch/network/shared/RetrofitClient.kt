package com.example.coagusearch.network.shared

import android.content.Context
import android.content.SharedPreferences
import com.example.coagusearch.network.Appointment.model.AppointmentApi
import com.example.coagusearch.network.Auth.model.AuthApi
import com.example.coagusearch.network.Auth.model.AuthRepository
import com.example.coagusearch.network.Interceptors.AuthInterceptor
import com.example.coagusearch.network.Interceptors.LocaleInterceptor
import com.example.coagusearch.network.PatientData.model.PatientDataApi
import com.example.coagusearch.network.RegularMedication.model.RegularMedicationApi
import com.example.coagusearch.network.Users.model.UsersApi
import com.example.coagusearch.network.bloodOrderAndRecommendation.model.BloodOrderApi
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient(
    private val authInterceptor: AuthInterceptor,
    private val localeInterceptor: LocaleInterceptor
) {
    fun authApi(): AuthApi = getRetrofit().create(AuthApi::class.java)
    fun appointmentApi(): AppointmentApi = getRetrofit().create(AppointmentApi::class.java)
    fun regularMedicationApi(): RegularMedicationApi =
        getRetrofit().create(RegularMedicationApi::class.java)
    fun usersApi(): UsersApi = getRetrofit().create(UsersApi::class.java)
    fun patientDataApi():PatientDataApi=getRetrofit().create(PatientDataApi::class.java)
    fun bloodOrderApi():BloodOrderApi=getRetrofit().create(BloodOrderApi::class.java)
    private lateinit var pref: SharedPreferences

    private fun getRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClientBuilder = OkHttpClient.Builder()
        var apiUrl = "http://192.168.2.141:8080"
        val httpClient = okHttpClientBuilder
            .addInterceptor(authInterceptor)
            .addInterceptor(localeInterceptor)
            .protocols(listOf(Protocol.HTTP_2, Protocol.HTTP_1_1))
            .build()
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(httpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }
}