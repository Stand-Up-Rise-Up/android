package com.standupriseup.torbotdoctorrobot.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.standupriseup.torbotdoctorrobot.data.ResponseApi
import com.standupriseup.torbotdoctorrobot.data.ResultApi
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class paruViewModel: ViewModel() {
    private var dummyList: ArrayList<ResultApi> = arrayListOf()
    private var _listResult = MutableLiveData<List<ResultApi>>()
    var listResultApi: LiveData<List<ResultApi>> = _listResult
    private var list = arrayListOf<ResultApi>()
    private var endpoint = "https://us-central1-annular-garden-313509.cloudfunctions.net/uploadImage"
    private var endpointLocal = "http://192.168.1.9:9001"

    fun getDummyData(): ArrayList<ResultApi> {
        val client = OkHttpClient()
        val request = Request.Builder()
                .url("$endpointLocal/uploads")
                .build()

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
            override fun onResponse(call: Call, response: Response) {
                response.use{
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    val body = response.body?.string()
                    val diseaseList = JSONObject(body).getJSONArray("result")

                    for (index in 0 until diseaseList.length()) {
                        val result = diseaseList.getJSONObject(index)
                        val disease: String = result.getString("disease")
                        val percent: Double = result.getDouble("percent")

                        list.add(
                            ResultApi(
                                disease, percent
                            )
                        )

                        _listResult.postValue(list)
                    }
                }
            }
        })

        return list
    }
}
