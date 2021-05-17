package com.standupriseup.torbotdoctorrobot.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.standupriseup.torbotdoctorrobot.R
import com.standupriseup.torbotdoctorrobot.databinding.ActivityParuBinding

class ParuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParuBinding

    companion object {
        const val NAME = "name"
        const val DESC = "desc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityParuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding?.nameDisease?.text = intent.getStringExtra(NAME)
        binding?.descDisease?.text = intent.getStringExtra(DESC)
    }
}