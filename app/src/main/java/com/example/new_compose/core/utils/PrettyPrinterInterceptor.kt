package com.example.new_compose.core.utils



import android.util.Log
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import org.json.JSONObject
import java.nio.charset.Charset

class PrettyPrinterInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()

        logRequest(request)

        val response = chain.proceed(request)

        logResponse(response, request)

        return response
    }

    private fun logRequest(request: Request) {

        try {
            Log.d("╔══ REQUEST ══╗", "================================")

            Log.d("METHOD", request.method)
            Log.d("URL", request.url.toString())

            // Headers
            Log.d("HEADERS", request.headers.toString())

            // Body
            request.body?.let { body ->
                val buffer = Buffer()
                body.writeTo(buffer)
                val bodyStr = buffer.readUtf8()

                Log.d("BODY", prettyJson(bodyStr))
            }

            Log.d("╚═════════════╝", "================================")

        } catch (e: Exception) {
            Log.e("Printer", "Request logging failed")
        }
    }

    private fun logResponse(response: Response, request: Request) {

        try {
            val responseBody = response.peekBody(Long.MAX_VALUE)

            Log.d("╔══ RESPONSE ══╗", "================================")
            Log.d("URL", request.url.toString())
            Log.d("CODE", response.code.toString())

            Log.d("BODY", prettyJson(responseBody.string()))

            Log.d("╚══════════════╝", "================================")

        } catch (e: Exception) {
            Log.e("Printer", "Response logging failed")
        }
    }

    private fun prettyJson(raw: String): String {
        return try {
            val json = JSONObject(raw)
            json.toString(4)
        } catch (e: Exception) {
            raw
        }
    }
}