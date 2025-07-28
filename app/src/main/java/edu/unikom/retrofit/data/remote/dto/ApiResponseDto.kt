package edu.unikom.retrofit.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ApiResponseDto(
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val data: List<UserDto>
) 