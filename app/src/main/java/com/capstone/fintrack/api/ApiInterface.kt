package com.capstone.fintrack.api

import com.capstone.fintrack.auth.LoginInfo
import com.capstone.fintrack.models.User
import com.capstone.fintrack.request.AnalysisListRequest
import com.capstone.fintrack.request.CategoryRequest
import com.capstone.fintrack.request.HomeDataRequest
import com.capstone.fintrack.request.SubmitCategoryRequest
import com.capstone.fintrack.request.SubmitRecordRequest
import com.capstone.fintrack.request.TransactionRequest
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


    @Headers("Content-Type: application/json")
    @POST("api/change-password.php")
    fun changePassword(@Body loginInfo: LoginInfo): Call<LoginInfo>

    @Headers("Content-Type: application/json")
    @POST("api/get-category-list.php")
    fun getCategoryList(@Body categoryRequest: CategoryRequest): Call<CategoryRequest>

    @Headers("Content-Type: application/json")
    @POST("api/submit-record.php")
    fun submitRecord(@Body submitRecordRequest: SubmitRecordRequest): Call<SubmitRecordRequest>

    @Headers("Content-Type: application/json")
    @POST("api/submit-category.php")
    fun submitCategory(@Body submitCategoryRequest: SubmitCategoryRequest): Call<SubmitCategoryRequest>

    @Headers("Content-Type: application/json")
    @POST("api/get-analysis-list.php")
    fun getAnalysisList(@Body analysisListRequest: AnalysisListRequest): Call<AnalysisListRequest>

    @Headers("Content-Type: application/json")
    @POST("api/signup.php")
    fun signupUser(@Body user: User): Call<User>

}