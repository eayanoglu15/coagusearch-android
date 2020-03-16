package com.example.coagusearch.network.shared

import com.example.coagusearch.network.Interceptors.AuthInterceptor
import io.reactivex.schedulers.Schedulers.single
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module
/**
 * Application dependency module, initiated by Koin in [MyApplication]
 */
val appModule = module {
    // Retrofit
    single { RetrofitClient(androidContext(), get()) }
    factory { get<RetrofitClient>().authApi() }
    factory { get<RetrofitClient>().usersApi() }
    factory { get<RetrofitClient>().regularMedicationApi() }
    factory { get<RetrofitClient>().appointmentApi()}
    single { AuthInterceptor(androidContext()) }
}