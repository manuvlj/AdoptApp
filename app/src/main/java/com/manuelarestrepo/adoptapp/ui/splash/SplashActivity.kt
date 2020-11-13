package com.manuelarestrepo.adoptapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.manuelarestrepo.adoptapp.R
import com.manuelarestrepo.adoptapp.ui.drawer.DrawerActivity
import com.manuelarestrepo.adoptapp.ui.login.LoginActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val timer = Timer()
        timer.schedule(
            timerTask {
                //super.onStart()
                // Check if user is signed in (non-null) and update UI accordingly.
                val auth = FirebaseAuth.getInstance().currentUser
                if (auth == null) {
                    goToLoginActivity()
                } else {
                    goToDrawerActivity()
                }
            }, 2000
        )
    }

    private fun goToDrawerActivity() {
        val intent = Intent(this, DrawerActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun goToLoginActivity() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}