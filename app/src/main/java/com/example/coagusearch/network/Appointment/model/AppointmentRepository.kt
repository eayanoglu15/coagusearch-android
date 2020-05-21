package com.example.coagusearch.network.Appointment.model

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.example.coagusearch.R
import com.example.coagusearch.network.Appointment.request.DeleteAppointmentsForUserRequest
import com.example.coagusearch.network.Appointment.request.SaveAppointmentRequest
import com.example.coagusearch.network.Appointment.response.UserAppointmentResponse
import com.example.coagusearch.network.Appointment.response.WeeklyAvailabilityResponse
import com.example.coagusearch.network.onFailureDialog
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.response.ApiResponse
import com.example.coagusearch.patient.NewAppointment
import com.example.coagusearch.patient.appointmentspage
import com.example.coagusearch.ui.dialog.showProgressLoading
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppointmentRepository(
    private val context: Context,
    private val retrofitClient: RetrofitClient
) {
    fun availableTimeSlotsForAppointment(context: Context): WeeklyAvailabilityResponse? {
        var appointmentResponse: WeeklyAvailabilityResponse? = null
        retrofitClient.appointmentApi().availableTimeSlotsForAppointment()
            .enqueue(object : Callback<WeeklyAvailabilityResponse> {
                override fun onFailure(call: Call<WeeklyAvailabilityResponse>, t: Throwable) {
                    // (context as MainActivity).LoginButton.text ="Fail"
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false, context)
                }

                override fun onResponse(
                    call: Call<WeeklyAvailabilityResponse>,
                    response: Response<WeeklyAvailabilityResponse>
                ) {
                    if (response.isSuccessful && response.body() is WeeklyAvailabilityResponse) {
                        println(response.message())
                        appointmentResponse = response.body()
                        println(appointmentResponse.toString())
                        (context as NewAppointment).gotTheDate(appointmentResponse!!)
                        showProgressLoading(false, context)
                    } else {
                        val errorResponse =
                            Gson().fromJson<ApiResponse>(
                                response.errorBody()?.string(),
                                ApiResponse::class.java
                            )?.message
                                ?: context.resources.getString(R.string.errorOccurred)
                        showProgressLoading(false, context)
                        val builder = AlertDialog.Builder(context, R.style.AlertDialogStyle)
                        builder.setTitle("Hata")
                        builder.setMessage(errorResponse)
                        builder.setPositiveButton("OK") { _,_ ->
                            (context as NewAppointment).onBackPressed()
                        }
                        val dialog: AlertDialog = builder.create()
                        dialog.show()
                    }
                }
            })
        return appointmentResponse
    }

    fun getMyAppointments(
        context: Context,
        appointmentspage: appointmentspage
    ): UserAppointmentResponse? {
        var appointmentResponse: UserAppointmentResponse? = null
        showProgressLoading(true, context)
        retrofitClient.appointmentApi().getMyAppointments()
            .enqueue(object : Callback<UserAppointmentResponse> {
                override fun onFailure(call: Call<UserAppointmentResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false, context)
                }

                override fun onResponse(
                    call: Call<UserAppointmentResponse>,
                    response: Response<UserAppointmentResponse>
                ) {
                    if (response.isSuccessful && response.body() is UserAppointmentResponse) {
                        println(response.message())
                        appointmentResponse = response.body()
                        if(appointmentspage.isAdded) {
                            appointmentspage.setAdapter(appointmentResponse!!)
                        }
                        showProgressLoading(false, context)
                    } else {
                        val errorResponse =
                            Gson().fromJson<ApiResponse>(
                                response.errorBody()?.string(),
                                ApiResponse::class.java
                            )?.message
                                ?: context.resources.getString(R.string.errorOccurred)
                        showProgressLoading(false, context)
                        onFailureDialog(context, errorResponse)
                    }
                }
            })
        return appointmentResponse
    }

    fun saveAppointmentForUser(
        saveAppointmentRequest: SaveAppointmentRequest,
        context: Context
    ): ApiResponse? {
        var apiResponse: ApiResponse? = null
        showProgressLoading(true,context)
        retrofitClient.appointmentApi().saveAppointmentForUser(saveAppointmentRequest)
            .enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false, context)
                }

                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {
                    if (response.isSuccessful && response.body() is ApiResponse) {
                        apiResponse = response.body()
                        showProgressLoading(false, context)
                        (context as NewAppointment).onBackPressed()
                    } else {
                        val errorResponse =
                            Gson().fromJson<ApiResponse>(
                                response.errorBody()?.string(),
                                ApiResponse::class.java
                            )?.message
                                ?: context.resources.getString(R.string.errorOccurred)
                        showProgressLoading(false, context)
                        onFailureDialog(context, errorResponse)
                    }
                }
            })
        return apiResponse
    }

    fun deleteAppointmentForUser(
        deleteAppointmentsForUserRequest: DeleteAppointmentsForUserRequest,
        context: Context
    ): ApiResponse? {
        var apiResponse: ApiResponse? = null
        showProgressLoading(true, context)
        retrofitClient.appointmentApi().deleteAppointmentForUser(deleteAppointmentsForUserRequest)
            .enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                    showProgressLoading(false, context)
                }

                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {
                    if (response.isSuccessful && response.body() is ApiResponse) {
                        apiResponse = response.body()
                        showProgressLoading(false, context)
                    } else {
                        val errorResponse =
                            Gson().fromJson<ApiResponse>(
                                response.errorBody()?.string(),
                                ApiResponse::class.java
                            )?.message
                                ?: context.resources.getString(R.string.errorOccurred)
                        showProgressLoading(false, context)
                        onFailureDialog(context, errorResponse)
                    }
                }
            })
        return apiResponse
    }


}
