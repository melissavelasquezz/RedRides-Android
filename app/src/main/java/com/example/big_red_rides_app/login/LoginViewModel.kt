package com.example.big_red_rides_app.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.big_red_rides_app.Screen
import com.example.big_red_rides_app.SessionManger
import com.example.big_red_rides_app.retrofit.LoginRequest
import com.example.big_red_rides_app.retrofit.RetrofitInstance


import com.example.big_red_rides_app.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val retrofitInstance: RetrofitInstance
): ViewModel() {
    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    data class UiState(
        val email: String = "",
        val password: String = "",
        val errorMessage: String? = null,
        val navEvent: UIEvent<Screen>? = null
    )

    fun onEmailChanged(email: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _uiStateFlow.value = _uiStateFlow.value.copy(password = password)
    }
    fun onLoginClicked(email: String, password: String){
        viewModelScope.launch {
            try {
                val user = retrofitInstance.userApiService.loginUser((LoginRequest(email, password)))
                SessionManger.currentUserId = user.id
                SessionManger.currentUser = user
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    navEvent = UIEvent(Screen.FindScreen),
                    errorMessage = null
                )

            } catch (e: Exception) {
                _uiStateFlow.value = _uiStateFlow.value.copy(
                    errorMessage = "Account does not exist. Try again or sign up"
                )
            }
        }

    }

    fun onSignUpClicked(){
        _uiStateFlow.value = _uiStateFlow.value.copy(
            navEvent = UIEvent(Screen.SignUpScreen)
        )

    }



}