package com.capstone.fintrack.api

import com.capstone.fintrack.auth.LoginInfo
import com.capstone.fintrack.request.HomeDataRequest
import com.capstone.fintrack.request.TransactionRequest
import com.capstone.fintrack.todelete.Patient
import com.capstone.fintrack.todelete.ProfileInfo
import com.capstone.fintrack.todelete.main.MedicalRecordRequest
import com.capstone.fintrack.todelete.main.PatientRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Headers


interface ApiInterface {
    @Headers("Content-Type: application/json")
    @POST("api/login.php")
    fun loginUser(@Body loginInfo: LoginInfo): Call<LoginInfo>

    @Headers("Content-Type: application/json")
    @POST("api/get-home-data.php")
    fun getHomeData(@Body homeDataRequest: HomeDataRequest): Call<HomeDataRequest>

    @Headers("Content-Type: application/json")
    @POST("api/get-transactions.php")
    fun getTransactions(@Body transactionRequest: TransactionRequest): Call<TransactionRequest>



//    to delete

    @Headers("Content-Type: application/json")
    @POST("api/profile.php")
    fun getProfile(@Body profileInfo: ProfileInfo): Call<ProfileInfo>

    @Headers("Content-Type: application/json")
    @POST("api/change-password.php")
    fun changePassword(@Body loginInfo: LoginInfo): Call<LoginInfo>

    @Headers("Content-Type: application/json")
    @POST("api/get-patient-list.php")
    fun getPatientList(@Body patientRequest: PatientRequest): Call<PatientRequest>

    @Headers("Content-Type: application/json")
    @POST("api/get-md-records.php")
    fun getMDRecords(@Body mdRequest: MedicalRecordRequest): Call<MedicalRecordRequest>

    @Headers("Content-Type: application/json")
    @POST("api/submit-patient-form.php")
    fun submitPatientForm(@Body patient: Patient): Call<Patient>


}