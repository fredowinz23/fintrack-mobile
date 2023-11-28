package com.capstone.fintrack.ui.analysis

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.fintrack.api.ApiInterface
import com.capstone.fintrack.api.RetrofitClient
import com.capstone.fintrack.api.UserSession
import com.capstone.fintrack.databinding.ActivityAnalysisBinding
import com.capstone.fintrack.request.AnalysisListRequest
import com.capstone.fintrack.ui.home.HomeAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnalysisActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnalysisBinding
    lateinit var builder: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAnalysisBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val type = intent.extras?.getString("type", "")

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.tvHeader.text = "$type Overview"

        getAnalysis(type!!)
    }

    private fun getAnalysis(type: String) {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        val userSession = UserSession(this)
        val apiRequest = AnalysisListRequest(userSession.username!!, type)
        val call = retrofitAPI.getAnalysisList(apiRequest)

        call.enqueue(object : Callback<AnalysisListRequest?> {
            override fun onResponse(call: Call<AnalysisListRequest?>, response: Response<AnalysisListRequest?>) {

                // we are getting response from our body
                // and passing it to our modal class.
                val responseFromAPI: AnalysisListRequest? = response.body()
                val groupLinear = LinearLayoutManager(this@AnalysisActivity)
                binding.rvList.layoutManager = groupLinear
                val data = responseFromAPI?.analysis_list!!

                val adapter = AnalysisAdapter(this@AnalysisActivity, data)
                binding.rvList.adapter = adapter

            }

            override fun onFailure(call: Call<AnalysisListRequest?>, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.

                Toast.makeText(
                    this@AnalysisActivity,
                    t.message.toString(),
                    Toast.LENGTH_LONG
                ).show()

                Log.e("Login Error", t.message.toString())
            }

        })
    }
}