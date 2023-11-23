package com.capstone.fintrack.ui.transactions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.fintrack.MainActivity
import com.capstone.fintrack.api.ApiInterface
import com.capstone.fintrack.api.RetrofitClient
import com.capstone.fintrack.api.UserSession
import com.capstone.fintrack.databinding.FragmentTransactionsBinding
import com.capstone.fintrack.request.TransactionRequest
import retrofit2.Call
import retrofit2.Response

class TransactionFragment : Fragment() {
    private var _binding: FragmentTransactionsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentTransactionsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        getHomeData()

        return root
    }

    private fun getHomeData() {
        val retrofit = RetrofitClient.getInstance(context)
        val retrofitAPI = retrofit.create(ApiInterface::class.java)

        val userSession = UserSession(context)
        val dataRequest = TransactionRequest(userSession.username!!)
        val call = retrofitAPI.getTransactions(dataRequest)

        call.enqueue(object : retrofit2.Callback<TransactionRequest?> {
            override fun onResponse(call: Call<TransactionRequest?>, response: Response<TransactionRequest?>) {

                binding.progressBar.visibility = View.GONE

                val responseFromAPI: TransactionRequest? = response.body()

                val groupLinear = LinearLayoutManager(context)
                binding.rvList.layoutManager = groupLinear
                val data = responseFromAPI?.record_list!!

                val adapter = TransactionAdapter(context, data)
                binding.rvList.adapter = adapter
            }

            override fun onFailure(call: Call<TransactionRequest?>, t: Throwable) {

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