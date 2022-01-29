package com.example.fortytworace.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.example.core.utils.PreferenceEntity
import com.example.core.utils.UserPreference
import com.example.fortytworace.R
import com.example.fortytworace.databinding.ActivitySplashscreenBinding
import com.example.fortytworace.ui.businesslist.BusinessActivity
import com.example.fortytworace.ui.login.Login
import com.example.fortytworace.util.CheckConnection
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

@ExperimentalCoroutinesApi
class Splashscreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding

    private var mPreference: UserPreference? = null
    private val mPreferenceEntity: PreferenceEntity get() = mPreference!!.getPref()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        mPreference = UserPreference(this)

        // animation
        val rightToLeftAnimation =
            android.view.animation.AnimationUtils.loadAnimation(this, R.anim.right_animation)

        binding.ivLogoSplashscreen.animation = rightToLeftAnimation

        val checkConnection = CheckConnection.internetAvailable(this)
        if (!checkConnection) {
            Toast.makeText(this, "No Connection Detected", Toast.LENGTH_SHORT).show()
        } else {
            Timber.d("checkConnectionClass : $checkConnection")
        }

        val splashTread: Thread = object : Thread() {
            override fun run() {
                try {
                    sleep(2000)
                } catch (e: InterruptedException) {
                    // do nothing
                } finally {
//                    val mIntent = Intent(this@SplashScreenActivity, LoginActivity::class.java)
//                    startActivity(mIntent)
                    if (mPreferenceEntity.isLogin == true) {
                        val mIntent = Intent(this@Splashscreen, BusinessActivity::class.java)
//                        val i = Intent(this, HomeActivity::class.java)
////                        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
////                        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(mIntent)
//                        finish()
                    } else {
                        val mIntent = Intent(this@Splashscreen, Login::class.java)
                        startActivity(mIntent)
                        finish()
                    }
                }

            }

        }

        splashTread.start()

    }
}