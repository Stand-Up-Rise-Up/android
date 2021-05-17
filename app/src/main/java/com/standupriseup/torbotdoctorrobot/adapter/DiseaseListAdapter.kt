package com.standupriseup.torbotdoctorrobot.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.standupriseup.torbotdoctorrobot.R
import com.standupriseup.torbotdoctorrobot.data.Disease

class DiseaseListAdapter(private val listDisease: ArrayList<Disease>) : RecyclerView.Adapter<DiseaseListAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_disease, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val disease = listDisease[position]
        Glide.with(holder.itemView.context)
                .load(disease.icon)
                .apply(RequestOptions().override(55, 55))
                .into(holder.icon)
        holder.name.text = disease.name
        holder.shortDesc.text = disease.shortDesc
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listDisease[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int {
        return listDisease.size
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Disease)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.id_name_rv)
        var shortDesc: TextView  = itemView.findViewById(R.id.id_shortdesc_rv)
        var icon: ImageView = itemView.findViewById(R.id.id_icon_rv)
    }
}