package com.culturapp.culturapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlin.concurrent.thread

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        Handler().postDelayed({
            NextScreen()
        }, 10000)

    }

    override fun onResume() {
        super.onResume()

    }

    fun NextScreen(){
        startActivity(Intent(this,MainActivity::class.java))
        finish()
    }
}

