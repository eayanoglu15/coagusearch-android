package com.example.coagusearch.network.shared

import android.content.Context
import android.content.SharedPreferences
import com.example.coagusearch.network.Appointment.model.AppointmentApi
import com.example.coagusearch.network.Auth.model.AuthApi
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient(
    //private val context: Context
) {
    fun authApi(): AuthApi = getRetrofit().create(AuthApi::class.java)
    fun appointmentApi(): AppointmentApi = getRetrofit().create(AppointmentApi::class.java)
   /* fun surveyApi(): SurveyApi = getRetrofit().create(SurveyApi::class.java)
    fun userApi(): UserApi = getRetrofit().create(UserApi::class.java)
    fun uploadApi(): UploadApi = getRetrofit().create(UploadApi::class.java)
    fun voiceOverApi(): VoiceOverApi = getRetrofit().create(VoiceOverApi::class.java)
    fun configApi(): ConfigApi = getRetrofit().create(ConfigApi::class.java)
    fun popupQuestionApi(): PopupQuestionApi = getRetrofit().create(PopupQuestionApi::class.java)
    fun trackingApi(): TrackingApi = getRetrofit().create(TrackingApi::class.java)
    fun dailyActivitiesApi(): DailyActivitiesApi = getRetrofit().create(DailyActivitiesApi::class.java)
    fun medicalApi(): MedicalApi = getRetrofit().create(MedicalApi::class.java)
    fun notificationApi(): NotificationApi = getRetrofit().create(NotificationApi::class.java)
    fun dashboardApi(): DashboardApi = getRetrofit().create(DashboardApi::class.java)
    fun calendarApi(): CalendarApi = getRetrofit().create(CalendarApi::class.java)
    fun periodTrackerApi(): PeriodTrackerApi = getRetrofit().create(PeriodTrackerApi::class.java)
    fun migraineApi(): MigraineApi = getRetrofit().create(MigraineApi::class.java)

    */

    private lateinit var pref: SharedPreferences

    private fun getRetrofit(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClientBuilder = OkHttpClient.Builder()

        var apiUrl ="http://172.16.140.190:8080"     //context.getString(R.string.api_url)
        /*
        if (BuildConfig.DEBUG) {
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
            pref = PreferenceHelper.customPrefs(context, PREF_CUSTOM_API_URL)
            if (pref.contains(KEY_CUSTOM_API_URL)) {
                apiUrl = pref.getString(KEY_CUSTOM_API_URL, apiUrl) ?: ""
            }
            //okHttpClientBuilder.addInterceptor(fakeInterceptor)
        }

         */


        val httpClient = okHttpClientBuilder
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