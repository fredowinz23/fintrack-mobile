package com.capstone.fintrack.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.fintrack.R
import com.capstone.fintrack.models.Account

class HomeAdapter(private var context: Context?, private val mList: List<Account>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_account, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]

        holder.name.text = item.name
        holder.income.text = item.income.toString()
        holder.expense.text = item.expense.toString()
        holder.balance.text = item.balance.toString()
//        holder.item.setOnClickListener {
//            val intent = Intent(context, MedicalRecordsActivity::class.java)
//            intent.putExtra("patientId", item.id)
//            (context as PatientListActivity).startActivity(intent)
//        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val income: TextView = itemView.findViewById(R.id.tvIncome)
        val expense: TextView = itemView.findViewById(R.id.tvExpense)
        val balance: TextView = itemView.findViewById(R.id.tvBalance)
        val name: TextView = itemView.findViewById(R.id.tvName)
        val item: LinearLayout = itemView.findViewById(R.id.llItem)
    }
}
