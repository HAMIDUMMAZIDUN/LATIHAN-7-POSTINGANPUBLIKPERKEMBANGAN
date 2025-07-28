package edu.unikom.retrofit.domain.usecase

import android.util.Log
import edu.unikom.retrofit.domain.entity.User
import edu.unikom.retrofit.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(page: Int = 1): Result<List<User>> {
        Log.d("CLEAN_ARCH_DEMO", "🏗️ Domain Layer - UseCase executing")
        Log.d("CLEAN_ARCH_DEMO", "🏗️ Repository injected via Hilt: ${userRepository::class.simpleName}")
        
        val result = userRepository.getUsers(page)
        
        Log.d("CLEAN_ARCH_DEMO", "🏗️ Domain Layer - UseCase finished")
        return result
    }
} 