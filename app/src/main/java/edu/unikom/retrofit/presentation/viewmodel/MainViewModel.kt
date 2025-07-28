package edu.unikom.retrofit.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.unikom.retrofit.domain.usecase.GetUsersUseCase
import edu.unikom.retrofit.presentation.ui.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    init {
        Log.d("MVVM_DEMO", "✅ ViewModel created with Hilt injection")
        Log.d("MVVM_DEMO", "✅ UseCase injected: ${getUsersUseCase::class.simpleName}")
        fetchUsers()
    }

    fun fetchUsers(page: Int = 1) {
        Log.d("MVVM_DEMO", "🔄 Starting fetchUsers - MVVM Pattern")
        viewModelScope.launch {
            Log.d("MVVM_DEMO", "📱 Setting UI State to Loading")
            _uiState.value = UiState.Loading
            
            Log.d("MVVM_DEMO", "🏗️ Calling Use Case (Clean Architecture)")
            getUsersUseCase(page)
                .onSuccess { users ->
                    Log.d("MVVM_DEMO", "✅ Success: Received ${users.size} users")
                    Log.d("MVVM_DEMO", "📱 Updating UI State to Success")
                    _uiState.value = UiState.Success(users)
                }
                .onFailure { throwable ->
                    Log.e("MVVM_DEMO", "❌ Error: ${throwable.message}")
                    Log.d("MVVM_DEMO", "📱 Updating UI State to Error")
                    _uiState.value = UiState.Error(
                        throwable.message ?: "Unknown error occurred"
                    )
                }
        }
    }

    fun refreshUsers() {
        Log.d("MVVM_DEMO", "🔄 Refresh triggered from UI")
        fetchUsers()
    }
} 