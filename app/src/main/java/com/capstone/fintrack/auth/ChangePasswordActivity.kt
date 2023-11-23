package com.capstone.fintrack.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.capstone.fintrack.MainActivity
import com.capstone.fintrack.api.ApiInterface
import com.capstone.fintrack.api.RetrofitClient
import com.capstone.fintrack.api.UserSession
import com.capstone.fintrack.databinding.ActivityChangePasswordBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChangePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val username = intent.extras?.getString("username", "")

        binding.btnSubmit.setOnClickListener {
            if (binding.etPassword1.text.isEmpty() || binding.etPassword2.text.isEmpty()){
                Toast.makeText(
                    this,
                    "Password must not be empty",
                    Toast.LENGTH_LONG
                ).show()
            }
            else if (binding.etPassword1.text.toString() != binding.etPassword2.text.toString()){
                Toast.makeText(
                    this,
                    "Password not matched ${binding.etPassword1.text} == ${binding.etPassword2.text}",
                    Toast.LENGTH_LONG
                ).show()
            }
            else {
                postNewPasswordData(username!!,
                    binding.etPassword1.text.toString())
            }
        }
    }
    private fun postNewPasswordData(username: String, password: String) {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        binding.btnSubmit.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        val loginModal = LoginInfo(username, password)
        val call = retrofitAPI.changePassword(loginModal)

        call.enqueue(object : Callback<LoginInfo?> {
            override fun onResponse(call: Call<LoginInfo?>, response: Response<LoginInfo?>) {

                // we are getting response from our body
                // and passing it to our modal class.
                val responseFromAPI: LoginInfo? = response.body()


                if (!responseFromAPI?.profile?.username.isNullOrEmpty()){
                    val userSession = UserSession(this@ChangePasswordActivity)
                    userSession.edit?.putString("username", responseFromAPI?.profile?.username)
                    userSession.edit?.apply()
                    startActivity(Intent(this@ChangePasswordActivity, MainActivity::class.java))
                    finish()
                }
            }

            override fun onFailure(call: Call<LoginInfo?>, t: Throwable) {
                // setting text to our text view when
                // we get error response from API.

                Toast.makeText(
                    this@ChangePasswordActivity,
                    t.message.toString(),
                    Toast.LENGTH_LONG
                ).show()

                binding.progressBar.visibility = View.GONE
                binding.btnSubmit.visibility = View.VISIBLE
                Log.e("Login Error", t.message.toString())
            }

        })
    }
}