package com.capstone.fintrack.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.capstone.fintrack.api.ApiInterface
import com.capstone.fintrack.api.RetrofitClient
import com.capstone.fintrack.databinding.ActivitySignUpBinding
import com.capstone.fintrack.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    var fieldsValid = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        binding.linkLogin.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            finish()
        }

        binding.btnSignup.setOnClickListener {
            val user = User(0,
                binding.inputUsername.text.toString(),
                binding.inputFirstname.text.toString(),
                binding.inputLastname.text.toString(),
                binding.inputPhone.text.toString(),
                binding.inputPassword.text.toString()
            )
            fieldValidationChecker(user)
        }
    }

    private fun fieldValidationChecker(user: User) {
        binding.errorUsername.visibility = View.GONE
        binding.errorFirstname.visibility = View.GONE
        binding.errorLastname.visibility = View.GONE
        binding.errorPhone.visibility = View.GONE
        binding.errorPassword.visibility = View.GONE
        binding.errorPassword2.visibility = View.GONE

        // Required username
        if (binding.inputFirstname.text.isEmpty()){
            binding.errorFirstname.visibility = View.VISIBLE
            binding.errorFirstname.text = "This field is required!"
            fieldsValid = false
        }

        // Required username
        if (binding.inputLastname.text.isEmpty()){
            binding.errorLastname.visibility = View.VISIBLE
            binding.errorLastname.text = "This field is required!"
            fieldsValid = false
        }

        // Required username
        if (binding.inputUsername.text.isEmpty()){
            binding.errorUsername.visibility = View.VISIBLE
            binding.errorUsername.text = "This field is required!"
            fieldsValid = false
        }

        // Required username
        if (binding.inputPhone.text.isEmpty()){
            binding.errorPhone.visibility = View.VISIBLE
            binding.errorPhone.text = "This field is required!"
            fieldsValid = false
        }


        // Required username
        if (binding.inputPassword.text.isEmpty()){
            binding.errorPassword.visibility = View.VISIBLE
            binding.errorPassword.text = "This field is required!"
            fieldsValid = false
        }

        // Required username
        if (binding.inputPassword2.text.isEmpty()){
            binding.errorPassword2.visibility = View.VISIBLE
            binding.errorPassword2.text = "This field is required!"
            fieldsValid = false
        }

        //Password must match password 2
        if (binding.inputPassword.text.toString()!=binding.inputPassword2.text.toString()){
            binding.errorPassword2.visibility = View.VISIBLE
            binding.errorPassword2.text = "Passwords Not Matched"
            fieldsValid = false
        }

        if (fieldsValid){
            postSignUpData(user)
        }
    }

    private fun postSignUpData(user: User) {
        val retrofit = RetrofitClient.getInstance(this)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        binding.btnSignup.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE

        val call = retrofitAPI.signupUser(user)

        call.enqueue(object : Callback<User?> {
            override fun onResponse(call: Call<User?>, response: Response<User?>) {

                binding.progressBar.visibility = View.GONE
                binding.btnSignup.visibility = View.VISIBLE

                val responseFromAPI: User? = response.body()

                if (responseFromAPI?.success!!){
                    startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                    finish()
                    Toast.makeText(
                        this@SignUpActivity,
                        "You have successfully signed up. You may now log in",
                        Toast.LENGTH_LONG
                    ).show()
                }
                else{
                    if (responseFromAPI.error=="usernameAlreadyExists"){
                        binding.errorUsername.visibility = View.VISIBLE
                        binding.errorUsername.text = "Username Already Exists!"
                    }
                    else{
                        Toast.makeText(
                            this@SignUpActivity,
                            "Internet Connection Error",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }

            override fun onFailure(call: Call<User?>, t: Throwable) {

                Toast.makeText(
                    this@SignUpActivity,
                    "Internet Connection Error",
                    Toast.LENGTH_LONG
                ).show()

                binding.progressBar.visibility = View.GONE
                binding.btnSignup.visibility = View.VISIBLE
                Log.e("Login Error", t.message.toString())
            }

        })

    }
}