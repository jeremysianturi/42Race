package com.example.core.utils

import android.content.Context
import androidx.core.content.edit

class UserPreference(context: Context) {

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val USERNAME_LOGIN = "username_login"
        private const val TOKEN = "token"
        private const val TOKEN_TYPE = "token_type"
        private const val ISLOGIN = "islogin"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setPref(value: PreferenceEntity) {
        preferences.edit {
            putString(TOKEN, value.token)
            putString(TOKEN_TYPE, value.tokenType)
            putString(USERNAME_LOGIN, value.username)
            putBoolean(ISLOGIN, value.isLogin!!)
        }
    }

    fun getPref(): PreferenceEntity {

        val data = PreferenceEntity()
        data.token = preferences.getString(TOKEN, "Da3guA7KGPYzf_YDvGiGsaDULMCw856rwyMn9TQcH6W9M1kg8CcBRSmSI88sD-hj9L3zFZPKErF12VaM3rYn5oMd-xn0cZoEgFGvGt9diBPL5effii0CmHKM21_uYXYx")
        data.tokenType = preferences.getString(TOKEN_TYPE, "Bearer").toString()
        data.username = preferences.getString(USERNAME_LOGIN, "").toString()
        data.isLogin = preferences.getBoolean(ISLOGIN, false)

        return data
    }
}