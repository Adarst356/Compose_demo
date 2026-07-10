/// @Created by Adarsh Tiwari on 7/10/2026
/// Know more about author at https://www.linkedin.com/in/adarsh-tiwari-tr

package com.example.new_compose.modules.auth.login
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.new_compose.modules.auth.data.AuthRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repo: AuthRepo) : ViewModel() {
    /*    private val _loginResponse = MutableStateFlow<UiState<LoginResponse>>(UiState.None)
        val loginResponse: StateFlow<UiState<LoginResponse>> = _loginResponse*/

    var mobileNoTextState by mutableStateOf("")
    var passTextState by mutableStateOf("")

    /*  fun login() {
          viewModelScope.launch {
              repo.login(
                  req = LoginRequest(
                      userID = mobileNoTextState.trim(),
                      password = passTextState.trim()
                  )
              ) {
                  _loginResponse.value = it
              }
          }
      }*/

    /*
        fun validateOTP(otp: String? = null) {
            viewModelScope.launch {
                repo.validateOTP(
                    req = LoginRequest(
                        userID = mobileNoTextState.trim(),
                        password = passTextState.trim(),
                        otp = otp,
                        oTPType = 1,
                        oTPSession = _loginResponse.value.getDataOrNull()?.otpSession
                    )
                ) {
                    _loginResponse.value = it
                }
            }
        }
    */

    /* fun resetLoginResponse() {
         _loginResponse.value = UiState.None
     }*/
}