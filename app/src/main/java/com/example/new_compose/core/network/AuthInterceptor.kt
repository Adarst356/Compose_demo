/// @Created by Adarsh Tiwari on 7/10/2026
/// Know more about author at https://www.linkedin.com/in/adarsh-tiwari-tr

package com.example.new_compose.core.network

import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.Response
import android.content.Context
import com.example.new_compose.core.managers.SessionManager
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sessionManager: SessionManager
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        builder.addHeader(
            "Content-Type",
            "application/json")
        builder.addHeader(
            "Accept",
            "application/json"
        )
        sessionManager.userData?.let { user ->
            user.accessToken?.let {
                builder.addHeader(
                    "Authorization",
                    "Bearer $it"
                )
            }

        }

        return chain.proceed(builder.build())
    }
}