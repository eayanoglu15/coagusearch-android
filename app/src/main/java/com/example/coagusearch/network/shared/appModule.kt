package com.example.coagusearch.network.shared

import com.example.coagusearch.network.Appointment.model.AppointmentRepository
import com.example.coagusearch.network.Auth.model.AuthRepository
import com.example.coagusearch.network.Interceptors.AuthInterceptor
import com.example.coagusearch.network.Interceptors.LocaleInterceptor
import com.example.coagusearch.network.PatientData.model.PatientDataRepository
import com.example.coagusearch.network.RegularMedication.model.RegularMedicationRepository
import com.example.coagusearch.network.Users.model.UsersRepository
import com.example.coagusearch.network.bloodOrderAndRecommendation.model.BloodOrderRepository
import com.example.coagusearch.network.notifications.model.NotificationRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

/**
 * Application dependency module, initiated by Koin in [MyApplication]
 */
val appModule = module {
    val ctx by lazy { androidApplication() }
    // Singletons
    single { ContextModule(androidContext()) }

    // Retrofit
    single { RetrofitClient(get(), get()) }
    factory { get<RetrofitClient>().authApi() }
    factory { get<RetrofitClient>().usersApi() }
    factory { get<RetrofitClient>().regularMedicationApi() }
    factory { get<RetrofitClient>().appointmentApi() }
    factory { get<RetrofitClient>().patientDataApi() }
    factory { get<RetrofitClient>().bloodOrderApi() }
    factory{get<RetrofitClient>().notificationsApi()}
    single { AuthInterceptor(get()) }
    factory { LocaleInterceptor(get()) }
    //Repository
    factory { AuthRepository(androidContext(), get(), get()) }
    factory { UsersRepository(androidContext(), get()) }
    factory { RegularMedicationRepository(androidContext(), get()) }
    factory { AppointmentRepository(androidContext(), get()) }
    factory { PatientDataRepository(androidContext(), get()) }
    factory { BloodOrderRepository(androidContext(), get()) }
    factory { NotificationRepository(androidContext(),get()) }

}