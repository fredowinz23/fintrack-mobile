package com.capstone.fintrack

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.capstone.fintrack.databinding.ActivityMainBinding
import com.capstone.fintrack.ui.home.IncomeExpenseFormActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        navView.setupWithNavController(navController)
    }

    fun openOptionDialog(id: Int, name: String) {
        val view = layoutInflater.inflate(R.layout.dialog_option, null)
        val addIncome = view.findViewById(R.id.btnAddIncome) as AppCompatButton
        val addExpense = view.findViewById(R.id.btnAddExpense) as AppCompatButton
        val transferFund = view.findViewById(R.id.btnTransferFund) as AppCompatButton
        var builder = Dialog(this)

        builder.setContentView(view)

        builder.show()
        builder.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

        addIncome.setOnClickListener {
            val intent = Intent(this, IncomeExpenseFormActivity::class.java)
            intent.putExtra("accountId", id)
            intent.putExtra("accountName", name)
            intent.putExtra("type", "Income")
            startActivity(intent)

            builder.hide()
        }

        addExpense.setOnClickListener {
            val intent = Intent(this, IncomeExpenseFormActivity::class.java)
            intent.putExtra("accountId", id)
            intent.putExtra("accountName", name)
            intent.putExtra("type", "Expense")
            startActivity(intent)

            builder.hide()
        }
    }
}