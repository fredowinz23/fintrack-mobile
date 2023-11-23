package com.capstone.fintrack.ui.transactions

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
import com.capstone.fintrack.models.Record

class TransactionAdapter(private var context: Context?, private val mList: List<Record>) : RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_record, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]

        holder.amount.text = item.amount.toString()
        holder.type.text = item.type
        holder.account.text = item.account!!.name
        holder.category.text = item.category!!.name
        holder.date.text = item.dateAdded
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
        val amount: TextView = itemView.findViewById(R.id.tvAmount)
        val type: TextView = itemView.findViewById(R.id.tvType)
        val account: TextView = itemView.findViewById(R.id.tvAccount)
        val category: TextView = itemView.findViewById(R.id.tvCategory)
        val date: TextView = itemView.findViewById(R.id.tvDate)
        val item: LinearLayout = itemView.findViewById(R.id.llItem)
    }
}
