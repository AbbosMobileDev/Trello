package com.abisoft.trello.viewModel.auth
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abisoft.trello.model.repasitory.AuthRepository

class AuthViewModelFactory(private val repository: AuthRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            return AuthViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
