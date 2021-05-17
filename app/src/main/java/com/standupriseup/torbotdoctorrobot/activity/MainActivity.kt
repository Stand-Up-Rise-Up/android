package com.standupriseup.torbotdoctorrobot.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standupriseup.torbotdoctorrobot.R
import com.standupriseup.torbotdoctorrobot.adapter.diseaseListAdapter
import com.standupriseup.torbotdoctorrobot.data.Disease
import com.standupriseup.torbotdoctorrobot.data.DiseaseList
import com.standupriseup.torbotdoctorrobot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var list: ArrayList<Disease> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding?.progressbar?.visibility = View.VISIBLE
        binding?.diseasesList?.setHasFixedSize(true)
        list.addAll(DiseaseList.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        binding?.diseasesList?.layoutManager = LinearLayoutManager(this)
        val diseaseListAdapter = diseaseListAdapter(list)
        binding?.diseasesList?.adapter = diseaseListAdapter

        diseaseListAdapter.setOnItemClickCallback(object : diseaseListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Disease) {
                showSelectedDisease(data)
            }
        })

        binding?.progressbar?.visibility = View.INVISIBLE
    }

    private fun showSelectedDisease(data: Disease) {
        val moveToDetail = Intent(this, ParuActivity::class.java)
        moveToDetail.putExtra(ParuActivity.NAME, data.name)
        moveToDetail.putExtra(ParuActivity.DESC, data.desc)
        startActivity(moveToDetail)
    }
}