package com.capstone.fintrack.ui.categories

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.capstone.fintrack.R
import com.capstone.fintrack.api.ApiInterface
import com.capstone.fintrack.api.RetrofitClient
import com.capstone.fintrack.api.UserSession
import com.capstone.fintrack.databinding.ActivityCategoryFormBinding
import com.capstone.fintrack.models.Category
import com.capstone.fintrack.request.CategoryRequest
import com.capstone.fintrack.request.SubmitCategoryRequest
import com.capstone.fintrack.request.SubmitRecordRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFormActivity : AppCompatActivity() {

    lateinit var binding: ActivityCategoryFormBinding
    lateinit var builder: Dialog
    var categoryId = 0
    var categoryList: List<Category>? = null
    var accountId = 0
    var accountName = ""
    var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoryFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        type = intent.extras?.getString("type", "")!!

        binding.tvHeader.text = "$type Form"

        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btnSubmit.setOnClickListener {
            if (binding.etName.text.isEmpty()){
                Toast.makeText(
                    this@CategoryFormActivity,
                    "Fields Must not be empty",
                    Toast.LENGTH_LONG
                ).show()
            }
            else{
                submitRecord(binding.etName.text.toString())
            }
        }
    }

    private fun submitRecord(name: String) {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        val userSession = UserSession(this)
        val apiRequest = SubmitCategoryRequest(userSession.username!!, name, type)
        val call = retrofitAPI.submitCategory(apiRequest)

        call.enqueue(object : Callback<SubmitCategoryRequest?> {
            override fun onResponse(call: Call<SubmitCategoryRequest?>, response: Response<SubmitCategoryRequest?>) {

                // we are getting response from our body
                // and passing it to our modal class.
                val responseFromAPI: SubmitCategoryRequest? = response.body()
                Toast.makeText(
                    this@CategoryFormActivity,
                    "Category Added",
                    Toast.LENGTH_LONG
                ).show()
                finish()

            }

            override fun onFailure(call: Call<SubmitCategoryRequest?>, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(
                    this@CategoryFormActivity,
                    t.message.toString(),
                    Toast.LENGTH_LONG
                ).show()

                Log.e("Login Error", t.message.toString())
            }

        })
    }

}