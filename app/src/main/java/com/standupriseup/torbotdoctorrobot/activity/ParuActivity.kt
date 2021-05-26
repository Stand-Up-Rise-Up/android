package com.standupriseup.torbotdoctorrobot.activity

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.standupriseup.torbotdoctorrobot.R
import com.standupriseup.torbotdoctorrobot.data.ResponseApi
import com.standupriseup.torbotdoctorrobot.data.ResultApi
import com.standupriseup.torbotdoctorrobot.databinding.ActivityParuBinding
import com.standupriseup.torbotdoctorrobot.viewModel.paruViewModel

class ParuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityParuBinding
    private val paruViewModel: paruViewModel by viewModels()
    private var CAMERA_REQUEST_CODE = 0
    private var IMAGE_REQUEST_CODE = 456
    private var PERMISSION_REQUEST_CODE = 0
    private var list = listOf<ResultApi>()

    companion object {
        const val NAME = "name"
        const val DESC = "desc"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityParuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameDisease.text = intent.getStringExtra(NAME)
        binding.descDisease.text = intent.getStringExtra(DESC)

        binding.imgPick.setOnClickListener{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permission = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_REQUEST_CODE)
                } else {
                    pickFromGallery()
                }
            } else {
                pickFromGallery()
            }
        }

        binding.detectButton.setOnClickListener{
            binding.progressbar.visibility = View.VISIBLE
            processData()
        }

        binding.reloadButton.setOnClickListener{
            finish()
            startActivity(getIntent())
        }
    }

    private fun pickFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        resultLauncher.launch(intent)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            PERMISSION_REQUEST_CODE->{
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickFromGallery()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            binding.imgPick.setImageURI(data?.data)
        }
    }

    private fun processData() {
        binding.fieldDetectResult.visibility = View.GONE
        list = listOf()
        paruViewModel.getDummyData()

        paruViewModel.listResultApi.observe(this, {
            list = it
            showData()
        })
    }

    private fun showData() {
        if (list[0].percent < 70) {
            binding.detectResult.setText(R.string.failedToDetect)
            binding.percentResult.setText(R.string.failedToDetectPercent)
        } else {
            if (list[0].disease == "Sehat") {
                binding.fieldDetectResult.setBackgroundColor(ContextCompat.getColor(this, R.color.teal_200))
            } else {
                binding.fieldDetectResult.setBackgroundColor(ContextCompat.getColor(this, R.color.magenta_200))
            }

            binding.detectResult.text = list[0].disease
            binding.percentResult.text = list[0].percent.toString() + "%"
        }

        binding.fieldDetectResult.visibility = View.VISIBLE
        binding.progressbar.visibility = View.GONE
        binding.detectButton.visibility = View.GONE
        binding.reloadButton.visibility = View.VISIBLE
    }
}