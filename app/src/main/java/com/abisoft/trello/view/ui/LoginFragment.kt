package com.abisoft.trello.view.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.abisoft.trello.R
import com.abisoft.trello.model.LoginRequest
import com.abisoft.trello.model.TokenManager
import com.abisoft.trello.model.repasitory.AuthRepository
import com.abisoft.trello.viewModel.auth.AuthViewModel
import com.abisoft.trello.viewModel.auth.AuthViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var editTextLogin: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonLogin: Button

    private val authViewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(AuthRepository(TokenManager(requireContext())))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        editTextLogin = view.findViewById(R.id.et_email)
        editTextPassword = view.findViewById(R.id.et_password)
        buttonLogin = view.findViewById(R.id.btn_login)

        buttonLogin.setOnClickListener {
            val login = editTextLogin.text.toString()
            val password = editTextPassword.text.toString()
            loginUser(login, password)
        }

        observeLoginResponse()

        return view
    }

    private fun loginUser(login: String, password: String) {
        val loginRequest = LoginRequest(login, password)
        authViewModel.login(loginRequest)
    }

    private fun observeLoginResponse() {
        authViewModel.loginResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response != null) {
                Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                // Tokenni olish va saqlash

                val token = authViewModel.getToken()
                // TasksFragment ga o'ting
            } else {
                Toast.makeText(requireContext(), "Login failed", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
