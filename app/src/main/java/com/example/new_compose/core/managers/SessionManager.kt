/// @Created by Adarsh Tiwari on 7/10/2026
/// Know more about author at https://www.linkedin.com/in/adarsh-tiwari-tr

package com.example.new_compose.core.managers

import com.example.new_compose.core.common.data.UserData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {


    var userData: UserData? = null


    fun saveUserData(data: UserData) {
        userData = data
    }


    fun clearSession() {
        userData = null
    }
}
