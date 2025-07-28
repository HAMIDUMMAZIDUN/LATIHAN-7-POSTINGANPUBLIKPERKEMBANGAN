package edu.unikom.retrofit.data.remote.api

import edu.unikom.retrofit.data.remote.dto.ApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/users")
    suspend fun getUsers(@Query("page") page: Int = 1): ApiResponseDto
} 