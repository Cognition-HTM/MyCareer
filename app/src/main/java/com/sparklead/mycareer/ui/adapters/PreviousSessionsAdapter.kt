package com.sparklead.mycareer.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.PreviousSessions
import kotlinx.android.synthetic.main.item_previous_sessions.view.*

class PreviousSessionsAdapter (
    val context: Context,
    val item : ArrayList<PreviousSessions>
    ):RecyclerView.Adapter<PreviousSessionsAdapter.PreviousSessionsViewHolder>(){

    inner class PreviousSessionsViewHolder(view : View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousSessionsViewHolder {
        return PreviousSessionsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_previous_sessions,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PreviousSessionsViewHolder, position: Int) {
        val model = item[position]

        holder.itemView.tv_previous_sessions.text = model.title
    }

    override fun getItemCount(): Int {
        return item.size
    }
}