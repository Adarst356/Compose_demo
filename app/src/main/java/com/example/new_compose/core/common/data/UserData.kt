package com.example.new_compose.core.common.data

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class UserData(
	val roleId: Int? = null,
	val accessToken: String? = null,
	val expiresAt: String? = null,
	val refreshToken: String? = null
) : Parcelable
