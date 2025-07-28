package edu.unikom.retrofit

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import edu.unikom.retrofit.databinding.ActivityMainBinding
import edu.unikom.retrofit.presentation.adapter.UserAdapter
import edu.unikom.retrofit.presentation.ui.UiState
import edu.unikom.retrofit.presentation.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: UserAdapter
    private val viewModel: MainViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("VIEW_BINDING_DEMO", "📱 Inflating layout with View Binding")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        Log.d("HILT_DEMO", "🔧 ViewModel injected via Hilt: ${viewModel::class.simpleName}")
        
        setupRecyclerView()
        observeUiState()
        setupSwipeRefresh()
    }
    
    private fun setupRecyclerView() {
        Log.d("VIEW_BINDING_DEMO", "📱 Setting up RecyclerView with View Binding")
        userAdapter = UserAdapter()
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }
    
    private fun observeUiState() {
        Log.d("MVVM_DEMO", "👀 Setting up LiveData observation - MVVM Pattern")
        viewModel.uiState.observe(this) { state ->
            Log.d("MVVM_DEMO", "👀 UI State changed: ${state::class.simpleName}")
            when (state) {
                is UiState.Loading -> {
                    Log.d("MVVM_DEMO", "📱 Showing Loading UI")
                    showLoading(true)
                }
                is UiState.Success -> {
                    Log.d("MVVM_DEMO", "📱 Showing Success UI with ${state.users.size} users")
                    showLoading(false)
                    userAdapter.submitList(state.users)
                }
                is UiState.Error -> {
                    Log.d("MVVM_DEMO", "📱 Showing Error UI: ${state.message}")
                    showLoading(false)
                    showError(state.message)
                }
            }
        }
    }
    
    private fun setupSwipeRefresh() {
        Log.d("VIEW_BINDING_DEMO", "📱 Setting up SwipeRefresh with View Binding")
        binding.swipeRefresh.setOnRefreshListener {
            Log.d("MVVM_DEMO", "🔄 Pull-to-refresh triggered")
            viewModel.refreshUsers()
            binding.swipeRefresh.isRefreshing = false
        }
    }
    
    private fun showLoading(isLoading: Boolean) {
        Log.d("VIEW_BINDING_DEMO", "📱 Updating UI elements via View Binding - Loading: $isLoading")
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.rvUsers.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
    
    private fun showError(message: String) {
        Log.d("MVVM_DEMO", "📱 Showing error message to user")
        Toast.makeText(this, "Error: $message", Toast.LENGTH_LONG).show()
    }
}