package com.example.coagusearch.network.Users.model

import android.content.Context
import com.example.coagusearch.*
import com.example.coagusearch.doctor.PatientBloodOrder
import com.example.coagusearch.doctor.doctorHomeFragment
import com.example.coagusearch.doctor.doctorMyPatient
import com.example.coagusearch.doctor.doctorPatientsFragment
import com.example.coagusearch.medicalTeam.medicalHomeFragment
import com.example.coagusearch.network.Users.request.AmbulancePatientRequest
import com.example.coagusearch.network.Users.request.PatientDetailRequest
import com.example.coagusearch.network.Users.request.UserBodyInfoSaveRequest
import com.example.coagusearch.network.Users.response.DoctorMainScreenResponse
import com.example.coagusearch.network.Users.response.PatientDetailResponse
import com.example.coagusearch.network.Users.response.PatientMainScreenResponse
import com.example.coagusearch.network.Users.response.UserResponse
import com.example.coagusearch.network.onFailureDialog
import com.example.coagusearch.network.shared.RetrofitClient
import com.example.coagusearch.network.shared.response.ApiResponse
import com.example.coagusearch.patient.UserInfoSingleton
import com.example.coagusearch.patient.accountPage
import com.example.coagusearch.patient.mainmenu
import com.example.coagusearch.ui.dialog.showProgressLoading
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UsersRepository(
    private val context: Context,
    private val retrofitClient: RetrofitClient
) {
    //TODO: Handle the Server error part which shows error on the screen
    fun getUserInfo(context: Context, which: Int): UserResponse? {
        var userResponse: UserResponse? = null
        showProgressLoading(true, context)
        retrofitClient.usersApi().getUserInfo()
            .enqueue(object : Callback<UserResponse> {
                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    println("Failure")

                }

                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    if (response.isSuccessful && response.body() is UserResponse) {
                        userResponse = response.body()
                        UserInfoSingleton.instance.userInfo = userResponse
                        if (which == 2) {
                            (context as MainActivity).goToActivity()
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
        return userResponse
    }


    fun getMyPatient(): List<UserResponse>? {
        var myPatients: List<UserResponse>? = null
        retrofitClient.usersApi().getMyPatients()
            .enqueue(object : Callback<List<UserResponse>> {
                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                }

                override fun onResponse(
                    call: Call<List<UserResponse>>,
                    response: Response<List<UserResponse>>
                ) {
                    myPatients = response.body()
                }

            })
        return myPatients
    }

    fun saveBodyInfo(
        saveBodyInfoSaveRequest: UserBodyInfoSaveRequest,
        context: Context
    ): ApiResponse? {
        var apiResponse: ApiResponse? = null
        showProgressLoading(true, context)
        retrofitClient.usersApi().saveBodyInfo(saveBodyInfoSaveRequest)
            .enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                }

                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {
                    if (response.isSuccessful && response.body() is ApiResponse) {
                        apiResponse = response.body()
                        showProgressLoading(false, context)
                        (context as accountPage).saved()
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

    fun getPatientMainScreen(
        context: Context,
        mainmenu: mainmenu
    ): PatientMainScreenResponse? {
        var patientMainScreenResponse: PatientMainScreenResponse? = null
        showProgressLoading(true, context)
        retrofitClient.usersApi().getPatientMainScreen()
            .enqueue(object : Callback<PatientMainScreenResponse> {
                override fun onFailure(call: Call<PatientMainScreenResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                }

                override fun onResponse(
                    call: Call<PatientMainScreenResponse>,
                    response: Response<PatientMainScreenResponse>
                ) {
                    if (response.isSuccessful && response.body() is PatientMainScreenResponse) {
                        patientMainScreenResponse = response.body()
                        showProgressLoading(false, context)
                        mainmenu.setView(patientMainScreenResponse!!)
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
        return patientMainScreenResponse
    }


    fun getDoctorMainScreen(
        context: Context,
        fragment: doctorHomeFragment
    ): DoctorMainScreenResponse? {
        var doctorMainScreenResponse: DoctorMainScreenResponse? = null
        showProgressLoading(true, context)
        retrofitClient.usersApi().getDoctorMainScreen()
            .enqueue(object : Callback<DoctorMainScreenResponse> {
                override fun onFailure(call: Call<DoctorMainScreenResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                }

                override fun onResponse(
                    call: Call<DoctorMainScreenResponse>,
                    response: Response<DoctorMainScreenResponse>
                ) {
                    if (response.isSuccessful && response.body() is DoctorMainScreenResponse) {
                        doctorMainScreenResponse = response.body()
                        fragment.attachToViews(doctorMainScreenResponse!!)
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
        return doctorMainScreenResponse
    }

    fun getMyPatients(
        context: Context,
        fragment: doctorPatientsFragment
    ): List<UserResponse>? {
        var myPatients: List<UserResponse>? = null
        showProgressLoading(true, context)
        retrofitClient.usersApi().getMyPatients()
            .enqueue(object : Callback<List<UserResponse>> {
                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                }

                override fun onResponse(
                    call: Call<List<UserResponse>>,
                    response: Response<List<UserResponse>>
                ) {
                    if (response.isSuccessful && response.body() is List<UserResponse>) {
                        myPatients = response.body()
                        fragment.setPatientList(myPatients!!)
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
        return myPatients
    }


    fun saveAmbulancePatient(
        patientID:AmbulancePatientRequest,
        context: Context,
        fragment: medicalHomeFragment
    ): ApiResponse? {
        var r: ApiResponse? = null
        showProgressLoading(true, context)
        retrofitClient.usersApi().saveAmbulancePatient(patientID)
            .enqueue(object : Callback<ApiResponse> {
                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                }
                override fun onResponse(
                    call: Call<ApiResponse>,
                    response: Response<ApiResponse>
                ) {
                    if (response.isSuccessful && response.body() is ApiResponse) {
                        r = response.body()
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
        return r
    }





    fun getPatientDetail(
        context: Context,
        patientDetailRequest: PatientDetailRequest
    ): PatientDetailResponse? {
        var patientDetailResponse: PatientDetailResponse? = null
        showProgressLoading(true, context)
        retrofitClient.usersApi().getPatientDetail(patientDetailRequest)
            .enqueue(object : Callback<PatientDetailResponse> {
                override fun onFailure(call: Call<PatientDetailResponse>, t: Throwable) {
                    onFailureDialog(context, t.toString())
                }

                override fun onResponse(
                    call: Call<PatientDetailResponse>,
                    response: Response<PatientDetailResponse>
                ) {
                    if (response.isSuccessful && response.body() is PatientDetailResponse) {
                        patientDetailResponse = response.body()
                        if(context is doctorMyPatient){
                            (context as doctorMyPatient).setData(patientDetailResponse!!)
                        }
                        else if( context is PatientBloodOrder){
                            (context as PatientBloodOrder).setData(patientDetailResponse!!)
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
        return patientDetailResponse
    }
}