package edu.unikom.retrofit.data.repository

import android.util.Log
import edu.unikom.retrofit.data.remote.api.ApiService
import edu.unikom.retrofit.domain.entity.User
import edu.unikom.retrofit.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : UserRepository {
    
    init {
        Log.d("HILT_DEMO", "🔧 Repository created with Hilt injection")
        Log.d("HILT_DEMO", "🔧 ApiService injected: ${apiService::class.simpleName}")
    }
    
    override suspend fun getUsers(page: Int): Result<List<User>> {
        Log.d("CLEAN_ARCH_DEMO", "💾 Data Layer - Repository executing")
        Log.d("CLEAN_ARCH_DEMO", "💾 Making API call with Retrofit + Coroutines")
        
        return try {
            val response = apiService.getUsers(page)
            Log.d("CLEAN_ARCH_DEMO", "💾 API Response received: ${response.data.size} users")
            
            val users = response.data.map { dto ->
                Log.d("CLEAN_ARCH_DEMO", "💾 Converting DTO to Domain: ${dto.firstName} ${dto.lastName}")
                dto.toDomain()
            }
            
            Log.d("CLEAN_ARCH_DEMO", "💾 Data Layer - Repository success")
            Result.success(users)
        } catch (e: Exception) {
            Log.e("CLEAN_ARCH_DEMO", "💾 Data Layer - Repository error: ${e.message}")
            Result.failure(e)
        }
    }
} 