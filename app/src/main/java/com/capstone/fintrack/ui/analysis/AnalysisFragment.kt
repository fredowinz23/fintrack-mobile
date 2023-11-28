package com.capstone.fintrack.ui.analysis

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.fintrack.databinding.FragmentAnalysisBinding
import com.capstone.fintrack.ui.home.IncomeExpenseFormActivity

class AnalysisFragment : Fragment() {
    private var _binding: FragmentAnalysisBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAnalysisBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.cvIncomeOverView.setOnClickListener {
            val intent = Intent(context, AnalysisActivity::class.java)
            intent.putExtra("type", "Income")
            startActivity(intent)
        }

        binding.cvExpenseOverView.setOnClickListener {
            val intent = Intent(context, AnalysisActivity::class.java)
            intent.putExtra("type", "Expense")
            startActivity(intent)
        }

        binding.cvAccountOverView.setOnClickListener {
            val intent = Intent(context, AnalysisActivity::class.java)
            intent.putExtra("type", "Account")
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}