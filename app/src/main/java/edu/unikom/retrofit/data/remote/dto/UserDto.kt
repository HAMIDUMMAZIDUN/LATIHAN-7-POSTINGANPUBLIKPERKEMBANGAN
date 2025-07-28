package edu.unikom.retrofit.data.remote.dto

import com.google.gson.annotations.SerializedName
import edu.unikom.retrofit.domain.entity.User

data class UserDto(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
) {
    fun toDomain(): User {
        return User(
            id = id,
            email = email,
            firstName = firstName,
            lastName = lastName,
            avatar = avatar
        )
    }
} 