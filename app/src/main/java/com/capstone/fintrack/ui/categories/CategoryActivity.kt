package com.capstone.fintrack.ui.categories

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.fintrack.api.ApiInterface
import com.capstone.fintrack.api.RetrofitClient
import com.capstone.fintrack.api.UserSession
import com.capstone.fintrack.databinding.ActivityCategoryBinding
import com.capstone.fintrack.request.CategoryRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryBinding
    lateinit var builder: Dialog
    var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        type = intent.extras?.getString("type", "")!!

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.tvHeader.text = "$type Overview"
        binding.btnAdd.text = "Add $type"

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, CategoryFormActivity::class.java)
            intent.putExtra("type", type)
            startActivity(intent)
        }

        getCategories(type)
    }

    override fun onResume() {
        super.onResume()

        getCategories(type)
    }

    private fun getCategories(type: String) {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        val userSession = UserSession(this)
        val apiRequest = CategoryRequest(userSession.username!!, type)
        val call = retrofitAPI.getCategoryList(apiRequest)

        call.enqueue(object : Callback<CategoryRequest?> {
            override fun onResponse(call: Call<CategoryRequest?>, response: Response<CategoryRequest?>) {

                // we are getting response from our body
                // and passing it to our modal class.
                val responseFromAPI: CategoryRequest? = response.body()
                val groupLinear = LinearLayoutManager(this@CategoryActivity)
                binding.rvList.layoutManager = groupLinear
                val data = responseFromAPI?.category_list!!

                val adapter = CategoryAdapter(this@CategoryActivity, data)
                binding.rvList.adapter = adapter

            }

            override fun onFailure(call: Call<CategoryRequest?>, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.

                Toast.makeText(
                    this@CategoryActivity,
                    t.message.toString(),
                    Toast.LENGTH_LONG
                ).show()

                Log.e("Login Error", t.message.toString())
            }

        })
    }
}