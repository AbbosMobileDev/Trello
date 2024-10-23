package com.abisoft.trello.viewModel.auth
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import com.abisoft.trello.model.repasitory.AuthRepository
import com.abisoft.trello.model.AuthResponse
import com.abisoft.trello.model.LoginRequest

class AuthViewModel(private val repository: AuthRepository) : ViewModel() {

    private val _loginResponse = MutableLiveData<AuthResponse?>()
    val loginResponse: LiveData<AuthResponse?> get() = _loginResponse

    fun login(loginRequest: LoginRequest) {
        viewModelScope.launch {
            try {
                val response = repository.login(loginRequest)
                if (response.isSuccessful) {
                    _loginResponse.value = response.body()
                } else {
                    _loginResponse.value = null
                }
            } catch (e: Exception) {
                _loginResponse.value = null
            }
        }
    }
    // Tokenni olish
    fun getToken(): String? {
        return repository.getToken()
    }

    // Tokenni tozalash
    fun clearToken() {
        repository.clearToken()
    }
}
