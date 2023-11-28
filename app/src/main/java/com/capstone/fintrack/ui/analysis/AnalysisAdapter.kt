package com.capstone.fintrack.ui.analysis

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
import com.capstone.fintrack.models.Analysis
import com.capstone.fintrack.models.Record

class AnalysisAdapter(private var context: Context?, private val mList: List<Analysis>) : RecyclerView.Adapter<AnalysisAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_analysis, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]

        holder.amount.text = item.amount.toString()
        holder.name.text = item.name
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val amount: TextView = itemView.findViewById(R.id.tvAmount)
        val name: TextView = itemView.findViewById(R.id.tvName)
        val item: LinearLayout = itemView.findViewById(R.id.llItem)
    }
}
