package com.example.core.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PreferenceEntity(
    var token: String? = "",
    var tokenType: String = "",
    var username: String = "",
    var isLogin : Boolean? = false
) : Parcelable