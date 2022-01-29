package com.example.fortytworace.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.core.utils.PreferenceEntity
import com.example.core.utils.UserPreference
import com.example.fortytworace.R
import com.example.fortytworace.databinding.ActivityLogin2Binding
import com.example.fortytworace.helper.Debounce.onThrottledClick
import com.example.fortytworace.ui.businesslist.BusinessActivity
import com.github.dhaval2404.form_validation.rule.NonEmptyRule
import com.github.dhaval2404.form_validation.validation.FormValidator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.system.exitProcess

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLogin2Binding

    private lateinit var mPreference: UserPreference
    private lateinit var mPreferenceEntity: PreferenceEntity

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogin2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        mPreference = UserPreference(this)
        mPreferenceEntity = mPreference.getPref()

        val username = mPreferenceEntity.username ?: ""
        val apiKey = mPreferenceEntity.token ?: ""

        if (username != "" || apiKey != "") {
            binding.edtUsernameLogin.setText(username)
            binding.edtApiKeyLogin.setText(apiKey)
        }

        if (mPreferenceEntity.isLogin == true) {
            val mIntent = Intent(this, BusinessActivity::class.java)
            startActivity(mIntent)
        }

        // onclclick listener
        binding.btnLogin.onThrottledClick {
            if (validationField()) {
                isValidField()
            } else {
                Toast.makeText(this, "Please fill out each form", Toast.LENGTH_SHORT)
                    .show()

            }
        }

    }

    private fun isValidField() {
        // disable button

        val username = binding.edtUsernameLogin.text.toString()
        val apiKey = binding.edtApiKeyLogin.text.toString()

        if (!apiKey.equals("Da3guA7KGPYzf_YDvGiGsaDULMCw856rwyMn9TQcH6W9M1kg8CcBRSmSI88sD-hj9L3zFZPKErF12VaM3rYn5oMd-xn0cZoEgFGvGt9diBPL5effii0CmHKM21_uYXYx")){
            Toast.makeText(this, "Wrong api key!", Toast.LENGTH_SHORT).show()
        } else {

            mPreferenceEntity.username = username
            mPreferenceEntity.token = apiKey

            mPreference.setPref(mPreferenceEntity)

            val mIntent = Intent(this, BusinessActivity::class.java)
            startActivity(mIntent)

        }

    }

    private fun validationField(): Boolean {
        return FormValidator.getInstance()
            .addField(
                binding.tilUsername,
                NonEmptyRule(getString(R.string.ERROR_FIELD_REQUIRED))
            )
            .addField(
                binding.tilApiKey,
                NonEmptyRule(getString(R.string.ERROR_FIELD_REQUIRED))
            )
            .validate()

    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
            exitProcess(0)
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Click one more to exit this application", Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(
            { doubleBackToExitPressedOnce = false },
            2000
        ) // review
    }
}