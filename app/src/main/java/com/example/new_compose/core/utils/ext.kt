package com.example.new_compose.core.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Parcelable
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.util.Patterns
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.reflect.KClass

/**
 * @Created by akash on 17-03-2025.
 * Know more about author at https://akash.cloudemy.in
 */
@Composable
fun isTablet(): Boolean {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    val smallestWidthDp = configuration.smallestScreenWidthDp

    return screenWidthDp >= 600 || smallestWidthDp >= 600
}
@SuppressLint("HardwareIds")
fun Context.androidId(): String {
    return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID) ?: "Unknown"
}
fun Double?.toNonZeroString(): String {
    return if (this == null || this == 0.0) "0" else this.toString()
}
fun String?.toNonZeroString(): String {
    return if (this == null || this.toDoubleOrNull() == 0.0||this.toDoubleOrNull()==null) "0" else this
}
fun <T> getError(key: String, response: Response<T>): String {
    return try {
        val errorBody = response.errorBody()?.charStream()?.readText()
        if (!errorBody.isNullOrEmpty()) {
            val json = JSONObject(errorBody)
            if (json.has(key)) {
                json.getString(key)
            } else {
                getDefaultMessage(response.code())
            }
        } else {
            getDefaultMessage(response.code())
        }
    } catch (e: Exception) {
        getDefaultMessage(response.code())
    }
}

private fun getDefaultMessage(code: Int): String {
    return when (code) {
        400 -> "400: Bad Request"
        401 -> "401: Unauthorized access"
        403 -> "403: Forbidden"
        404 -> "404: Resource not found"
        422 -> "422: Unprocessable Entity"
        500 -> "500: Internal server error"
        503 -> "503: Service unavailable"
        else -> "Unexpected error occurred (Code: $code)"
    }
}

fun Context.application(): Application {
    return applicationContext as Application
}

inline fun <reified T> T.toJson(): String {
    return Gson().toJson(this)
}

fun String?.isValidPrice(): Boolean {
   return this?.trim()?.isNotEmpty()==true && (this.trim().toDoubleOrNull()?:0.0)>0
}
fun String.isValidEmail(): Boolean {
    return this.isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

inline fun <reified T> String.fromJson(): T? {
    return try {
        Gson().fromJson(this, object : TypeToken<T>() {}.type)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

fun Context.showToast(message: String, bgColor: Int = android.graphics.Color.RED, textColor: Int = android.graphics.Color.WHITE) {
    val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)

    // Create a TextView programmatically
    val textView = TextView(this).apply {
        text = message
        setTextColor(textColor)
        textSize = 16f
        setPadding(24, 16, 24, 16)
        gravity = Gravity.CENTER
        background = GradientDrawable().apply {
            setColor(bgColor) // Background color
            cornerRadius = 48f // Rounded corners
        }
    }

    // Set custom view
    toast.view = textView
    toast.show()
}
/**
 * Find a [androidx.activity.ComponentActivity] from the current context.
 * By default Jetpack Compose project uses ComponentActivity for MainActivity,
 * It is a parent of [androidx.fragment.app.FragmentActivity] or [AppCompatActivity]
 */
fun Context.activity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.activity()
    else -> null
}

inline fun <reified T : Parcelable> Bundle?.getParcelableCompat(key: String): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        this?.getParcelable(key, T::class.java)
    } else {
        @Suppress("DEPRECATION")
        this?.getParcelable(key) as? T
    }
}

fun String.launchUrl(context: Context) {
    runCatching {
        val intent = Intent(Intent.ACTION_VIEW, this.toUri())
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

}



fun Uri.getFileFromUri(context: Context): File? {
    val inputStream = context.contentResolver.openInputStream(this) ?: return null
    val file = File(context.cacheDir, "picked_image_${System.currentTimeMillis()}.jpg")
    file.outputStream().use { output ->
        inputStream.copyTo(output)
    }
    return file
}
fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US).format(Date())
    return File.createTempFile("JPEG_${timeStamp}_", ".jpg", cacheDir)
}
fun String.openWhatsApp(context: Context) {
    val number=this
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("https://wa.me/$number")
            setPackage("com.whatsapp")
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        val fallbackIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/$number"))
        context.startActivity(fallbackIntent)
    }
}
fun String.openTelegram(context: Context) {
    val username = this.trimStart('@') // remove @ if present
    try {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = "https://t.me/$username".toUri()
            setPackage("org.telegram.messenger")
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        // Fallback to browser if Telegram is not installed
        val fallbackIntent = Intent(Intent.ACTION_VIEW, "https://t.me/$username".toUri())
        context.startActivity(fallbackIntent)
    }
}




fun String.dialNumber(context: Context) {
    runCatching {
        // Sanitize the number to remove any unwanted characters
        val sanitizedNumber = this.filter { it.isDigit() || it == '+' }
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$sanitizedNumber")
        }
        context.startActivity(intent)
    }
}
fun String.openMail(context: Context) {
    val email = this
    runCatching {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:$email")
        }
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent)
        }
    }
}



fun Context.gotoApplicationSettings() {
    startActivity(Intent().apply {
        action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
        data = Uri.parse("package:${packageName}")
    })
}
fun String.maskUtr(): String {
    val length = this.length
    return if (length > 4) {
        val stars = "*".repeat(length - 4)
        this.take(2) + stars + this.takeLast(2)
    } else {
        "*".repeat(length)
    }
}

fun String?.formatOpType(): String {
    if (this==null) return ""
    return if (contains(",")) replaceFirst(",", " (") + ")" else this
}
// Extension function to draw dashed line with margin
fun DrawScope.drawDashedLineWithMargin(
    marginTop:Float,
    dashWidth: Dp = 10.dp,
    dashGap: Dp = 6.dp,
    strokeWidth: Dp = 1.5.dp,
    lineColor: Color = Color.Gray,

) {
    // Convert Dp values to Px
    val dashWidthPx = dashWidth.toPx()
    val dashGapPx = dashGap.toPx()
    val strokeWidthPx = strokeWidth.toPx()
    val marginPx =  14.dp.toPx() // Convert margin to Px

    // Calculate the width available for the dashed line after applying margins
    val availableWidth = size.width - 2 * marginPx
    if (availableWidth > 0) {
        var startX = marginPx

        // Draw dashed line within the available width
        while (startX < size.width - marginPx) {
            drawLine(
                color = lineColor,
                start = Offset(startX, marginTop),
                end = Offset(startX + dashWidthPx, marginTop),
                strokeWidth = strokeWidthPx
            )
            startX += dashWidthPx + dashGapPx
        }
    }
}

fun String.trimStartNonAlphanumeric(): String {
    return this.dropWhile { !it.isLetterOrDigit() }
}
fun Context.isPackageAvailable(packageName: String): Boolean {
    return try {
        packageManager.getPackageInfo(packageName, 0)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun Bundle.json(): JSONObject {
    val json = JSONObject()
    for (key in keySet()) {
        val value = this.get(key)?.toString()
        json.put(key, value)
    }
    return json
}
fun String.toUpiIntent():Intent {
    val url=this
    val intent = Intent().apply {
        action = Intent.ACTION_VIEW
        data = url.toUri()
    }
    val chooser = Intent.createChooser(intent, "Pay with...").apply {
        flags = Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP
    }
     return chooser
}

fun String.addNewLineAtLastSpace(): String {
    val lastSpaceIndex = this.lastIndexOf(' ')
    return if (lastSpaceIndex != -1) {
        this.substring(0, lastSpaceIndex) + "\n" + this.substring(lastSpaceIndex + 1)
    } else {
        this // No space found, return original
    }
}
fun logLongXml(tag: String = "XML_LOG", xml: String) {
    val maxLogSize = 4000
    val formatted = xml.trimIndent()
    var i = 0
    while (i < formatted.length) {
        val end = (i + maxLogSize).coerceAtMost(formatted.length)
        val part = formatted.substring(i, end)
        Log.w(tag, part) // Or println(part) if not in Android
        i = end
    }
}

inline fun <reified T : Any> KClass<T>.listType(): Type {
    return object : TypeToken<List<T>>() {}.type
}


fun List<Pair<String,Int>>.statusText(code: Int?): String {
   return this.firstOrNull{it.second==code}?.first?:"All"
}
fun List<Pair<String,Int>>.statusCode(status: String): Int {
    return this.firstOrNull{it.first==status}?.second?:0
}
fun Context.captureAndShareWithAllApps( bitmap: Bitmap) {

    val file = File( cacheDir, "receipt_screenshot.png")
    val stream = FileOutputStream(file)
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    stream.flush()
    stream.close()
    val fileUri = FileProvider.getUriForFile(
        this,
        "${packageName}.provider",
        file
    )
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "image/*"
        putExtra(Intent.EXTRA_STREAM, fileUri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    val chooserIntent = Intent.createChooser(shareIntent, "Share via")
    startActivity(chooserIntent)
}



// Android 10+ (Q and above)
@RequiresApi(Build.VERSION_CODES.Q)
fun Context.savePdfToDownloadsQPlus(byteArray: ByteArray, fileName: String): Uri? {
    val contentValues = ContentValues().apply {
        put(MediaStore.Downloads.DISPLAY_NAME, fileName)
        put(MediaStore.Downloads.MIME_TYPE, "application/pdf")
        put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        put(MediaStore.Downloads.IS_PENDING, 1)
    }

    val resolver = contentResolver
    val uri = resolver.insert(MediaStore.Downloads.EXTERNAL_CONTENT_URI, contentValues)

    uri?.let {
        resolver.openOutputStream(it)?.use { stream ->
            stream.write(byteArray)
        }

        contentValues.clear()
        contentValues.put(MediaStore.Downloads.IS_PENDING, 0)
        resolver.update(uri, contentValues, null, null)
    }
    return uri
}

// Android 9 and below: SAF fallback
fun ActivityResultLauncher<Intent>.launchCreateFileIntent(  fileName: String) {
    val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "application/pdf"
        putExtra(Intent.EXTRA_TITLE, fileName)
    }
    launch(intent)
}

fun Context.savePdfViaUri( uri: Uri, byteArray: ByteArray) {
    contentResolver.openOutputStream(uri)?.use { it.write(byteArray) }
}
