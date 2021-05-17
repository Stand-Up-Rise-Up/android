package com.standupriseup.torbotdoctorrobot.data

import com.standupriseup.torbotdoctorrobot.R

object DiseaseList {
    private val diseaseName = arrayOf(
            "Penyakit paru-paru"
    )

    private  val diseaseShortDesc = arrayOf(
            "Deteksi penyakit paru-paru dari foto rontgen Anda"
    )

    private val diseaseDesc = arrayOf(
            "Unggah foto rontgen untuk memulai deteksi penyakit paru-paru. Pastikan gambar terlihat jelas."
    )

    private val diseaseIcon = arrayOf(
            R.drawable.lungs
    )

    val listData: ArrayList<Disease>
        get() {
            val list = arrayListOf<Disease>()
            for (position in diseaseName.indices) {
                val disease = Disease()
                disease.name = diseaseName[position]
                disease.shortDesc = diseaseShortDesc[position]
                disease.desc = diseaseDesc[position]
                disease.icon = diseaseIcon[position]
                list.add(disease)
            }
            return list
        }
}
