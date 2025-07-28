package edu.unikom.retrofit.domain.repository

import edu.unikom.retrofit.domain.entity.User

interface UserRepository {
    suspend fun getUsers(page: Int = 1): Result<List<User>>
} 