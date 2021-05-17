package com.standupriseup.torbotdoctorrobot.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import com.standupriseup.torbotdoctorrobot.R
import com.standupriseup.torbotdoctorrobot.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding?.nextButton?.setOnClickListener({
            val moveToMain = Intent(this, MainActivity::class.java)
            startActivity(moveToMain)
        })

    }
}
