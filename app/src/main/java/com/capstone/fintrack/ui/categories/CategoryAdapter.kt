package com.capstone.fintrack.ui.categories

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
import com.capstone.fintrack.models.Category
import com.capstone.fintrack.models.Record

class CategoryAdapter(private var context: Context?, private val mList: List<Category>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]
        holder.name.text = item.name
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val item: LinearLayout = itemView.findViewById(R.id.llItem)
    }
}
