package com.example.new_compose.core.managers

import android.content.Context
import com.example.new_compose.core.common.data.UserData
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import androidx.core.content.edit


@Singleton
class SessionManager @Inject constructor(
    @ApplicationContext private val context: Context
) {


    private val         prefs =
        context.getSharedPreferences(
            "user_session",
            Context.MODE_PRIVATE
        )


    private val gson = Gson()
    fun saveUserData(data: UserData) {
        val json = gson.toJson(data)
        prefs.edit {
            putString(
                "userData",
                json
            )
        }
    }


    val userData: UserData?
        get() {
            val json = prefs.getString(
                "userData",
                null
            )
            return if(json != null) {
                gson.fromJson(
                    json,
                    UserData::class.java
                )
            } else {
                null
            }
        }


    fun removeUserData(){
        prefs.edit {
            remove("userData")
        }

    }
}