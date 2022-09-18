package com.sparklead.mycareer.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.PreviousBlogs
import kotlinx.android.synthetic.main.item_previous_blogs.view.*

class PreviousBlogsAdapter(
    val context: Context,
    val item :ArrayList<PreviousBlogs>

):RecyclerView.Adapter<PreviousBlogsAdapter.PreviousBlogViewHolder>() {

    inner class PreviousBlogViewHolder(view : View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousBlogViewHolder {
        return PreviousBlogViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_previous_blogs,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PreviousBlogViewHolder, position: Int) {
        val model = item[position]

        holder.itemView.tv_blogs_title.text = model.title
    }

    override fun getItemCount(): Int {
        return item.size
    }
}