package edu.unikom.retrofit.presentation.ui

import edu.unikom.retrofit.domain.entity.User

sealed class UiState {
    object Loading : UiState()
    data class Success(val users: List<User>) : UiState()
    data class Error(val message: String) : UiState()
} 