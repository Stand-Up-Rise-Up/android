package com.standupriseup.torbotdoctorrobot.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.standupriseup.torbotdoctorrobot.R
import com.standupriseup.torbotdoctorrobot.adapter.DiseaseListAdapter
import com.standupriseup.torbotdoctorrobot.data.Disease
import com.standupriseup.torbotdoctorrobot.data.DiseaseList
import com.standupriseup.torbotdoctorrobot.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var list: ArrayList<Disease> = arrayListOf()
    private lateinit var rvDisease: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding?.progressBar?.visibility = View.VISIBLE

        binding?.diseasesList?.setHasFixedSize(true)
        list.addAll(DiseaseList.listData)
        showRecyclerList()
    }

    private fun showRecyclerList() {
        binding?.diseasesList?.layoutManager = LinearLayoutManager(this)
        val diseaseListAdapter = DiseaseListAdapter(list)
        binding?.diseasesList?.adapter = diseaseListAdapter

        diseaseListAdapter.setOnItemClickCallback(object : DiseaseListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Disease) {}
        })
        binding?.progressBar?.visibility = View.INVISIBLE
    }
}