package com.capstone.fintrack.ui.home

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
import com.capstone.fintrack.databinding.ActivityIncomeFormBinding
import com.capstone.fintrack.models.Category
import com.capstone.fintrack.request.CategoryRequest
import com.capstone.fintrack.request.SubmitRecordRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IncomeExpenseFormActivity : AppCompatActivity() {

    lateinit var binding: ActivityIncomeFormBinding
    lateinit var builder: Dialog
    var categoryId = 0
    var categoryList: List<Category>? = null
    var accountId = 0
    var accountName = ""
    var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityIncomeFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        accountId = intent.extras?.getInt("accountId", 0)!!
        accountName = intent.extras?.getString("accountName", "")!!
        type = intent.extras?.getString("type", "")!!

        binding.tvHeader.text = "Add $type to $accountName"

        binding.ivBack.setOnClickListener {
            finish()
        }

        getCategoryList()

        binding.llCategory.setOnClickListener {
            openCategoryOption()
        }

        binding.btnSubmit.setOnClickListener {
            if (categoryId==0 || binding.etAmount.text.isEmpty()){
                Toast.makeText(
                    this@IncomeExpenseFormActivity,
                    "Fields Must not be empty",
                    Toast.LENGTH_LONG
                ).show()
            }
            else{
                submitRecord(binding.etAmount.text.toString())
            }
        }
    }

    private fun submitRecord(amount: String) {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        val userSession = UserSession(this)
        val apiRequest = SubmitRecordRequest(userSession.username!!, amount, accountId, categoryId, type)
        val call = retrofitAPI.submitRecord(apiRequest)

        call.enqueue(object : Callback<SubmitRecordRequest?> {
            override fun onResponse(call: Call<SubmitRecordRequest?>, response: Response<SubmitRecordRequest?>) {

                // we are getting response from our body
                // and passing it to our modal class.
                val responseFromAPI: SubmitRecordRequest? = response.body()
                Toast.makeText(
                    this@IncomeExpenseFormActivity,
                    responseFromAPI!!.successMessage,
                    Toast.LENGTH_LONG
                ).show()
                finish()

            }

            override fun onFailure(call: Call<SubmitRecordRequest?>, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(
                    this@IncomeExpenseFormActivity,
                    t.message.toString(),
                    Toast.LENGTH_LONG
                ).show()

                Log.e("Login Error", t.message.toString())
            }

        })
    }

    private fun openCategoryOption() {
        val view = layoutInflater.inflate(R.layout.dialog_list, null)
        val itemList = view.findViewById(R.id.rvList) as RecyclerView
        builder = Dialog(this)

        builder.setContentView(view)

        builder.show()
        builder.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        val groupLinear = LinearLayoutManager(this@IncomeExpenseFormActivity)
        itemList.layoutManager = groupLinear
        val adapter = CategoryOptionAdapter(this@IncomeExpenseFormActivity, categoryList!!)
        itemList.adapter = adapter
    }

    private fun getCategoryList() {
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
                categoryList = responseFromAPI?.category_list

            }

            override fun onFailure(call: Call<CategoryRequest?>, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.
                Toast.makeText(
                    this@IncomeExpenseFormActivity,
                    t.message.toString(),
                    Toast.LENGTH_LONG
                ).show()

                Log.e("Login Error", t.message.toString())
            }

        })
    }
}