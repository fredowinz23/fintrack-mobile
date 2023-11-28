package com.capstone.fintrack.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.fintrack.api.ApiInterface
import com.capstone.fintrack.api.RetrofitClient
import com.capstone.fintrack.api.UserSession
import com.capstone.fintrack.databinding.FragmentHomeBinding
import com.capstone.fintrack.request.HomeDataRequest
import retrofit2.Call
import retrofit2.Response

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        getHomeData()

        return root
    }

    override fun onResume() {
        super.onResume()
        getHomeData()
    }

    private fun getHomeData() {
        val retrofit = RetrofitClient.getInstance(context)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        val userSession = UserSession(context)
        val dataRequest = HomeDataRequest(userSession.username!!)
        val call = retrofitAPI.getHomeData(dataRequest)

        call.enqueue(object : retrofit2.Callback<HomeDataRequest?> {
            override fun onResponse(call: Call<HomeDataRequest?>, response: Response<HomeDataRequest?>) {

                binding.progressBar.visibility = View.GONE

                val responseFromAPI: HomeDataRequest? = response.body()

                val groupLinear = GridLayoutManager(context, 2)
                binding.rvList.layoutManager = groupLinear
                val data = responseFromAPI?.account_list!!
                binding.tvTodaysBudget.text = responseFromAPI.budget.toString()
                binding.tvBalance.text = responseFromAPI.balance.toString()
                binding.tvTodaysExpense.text = responseFromAPI.expense.toString()
                binding.tvUser.text = responseFromAPI.user

                val adapter = HomeAdapter(context, data)
                binding.rvList.adapter = adapter
            }

            override fun onFailure(call: Call<HomeDataRequest?>, t: Throwable) {

                binding.progressBar.visibility = View.GONE

                Log.e("Login Error", t.message.toString())

                Toast.makeText(
                    context,
                    "Internet Connection Error",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}