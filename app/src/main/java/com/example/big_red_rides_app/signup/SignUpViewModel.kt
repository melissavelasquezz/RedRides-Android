package com.example.big_red_rides_app.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.example.big_red_rides_app.Screen
import com.example.big_red_rides_app.retrofit.RetrofitInstance
import com.example.big_red_rides_app.retrofit.User
import com.example.big_red_rides_app.retrofit.UserRequest
import com.example.big_red_rides_app.util.UIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val retrofitInstance: RetrofitInstance
): ViewModel() {

    private val _uiStateFlow = MutableStateFlow(UiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    data class UiState(
        val errorMessage: String? = null,
        val navEvent: UIEvent<Screen>? = null
    )
    fun OnCreateAccountClicked(
        name: String,
        email: String,
        password: String,
        year: String,
        phone: String
    ) {
       viewModelScope.launch {
           try {
               val userRequest = UserRequest(
                   name = name.trim(),
                   email = email.trim(),
                   password = password,
                   phone = phone.filter { it.isDigit() },
                   grad_year = year.toIntOrNull() ?: 0
               )
               val response = retrofitInstance.userApiService.createUser(userRequest)
               if (response.isSuccessful){
                   _uiStateFlow.value = _uiStateFlow.value.copy(
                       navEvent = UIEvent(Screen.LoginScreen),
                       errorMessage = null
                   )
               } else {
                   val errorBodyString = response.errorBody()?.string()
                   _uiStateFlow.value = _uiStateFlow.value.copy(
                       errorMessage = "Signup failed: ${errorBodyString ?: "Unknonw error"}"
                   )
               }

           } catch (e: Exception) {
               e.printStackTrace()
               _uiStateFlow.value = _uiStateFlow.value.copy(
                   errorMessage = "Could not create account. Please check your info."
               )
           }
       }
    }
}