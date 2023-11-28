package com.capstone.fintrack.ui.categories

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.fintrack.auth.LoginActivity
import com.capstone.fintrack.databinding.FragmentCategoriesBinding
import com.capstone.fintrack.ui.home.IncomeExpenseFormActivity

class CategoriesFragment : Fragment() {
    private var _binding: FragmentCategoriesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.cvIncomeCategory.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra("type", "Income")
            startActivity(intent)
        }
        binding.cvExpenseCategory.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra("type", "Expense")
            startActivity(intent)
        }
        binding.cvAccountCategory.setOnClickListener {
            val intent = Intent(context, CategoryActivity::class.java)
            intent.putExtra("type", "Account")
            startActivity(intent)
        }

        binding.cvLogout.setOnClickListener {
            val intent = Intent(context, LoginActivity::class.java)
            startActivity(intent)
        }
        

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}