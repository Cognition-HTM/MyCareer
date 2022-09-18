package com.sparklead.mycareer.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.models.PreviousBlogs
import com.sparklead.mycareer.ui.activities.ReadBlogsActivity
import kotlinx.android.synthetic.main.item_counselor.view.*

class CounselorPreviousBlogsAdapter(
    val context: Context,
    val item :ArrayList<PreviousBlogs>
): RecyclerView.Adapter<CounselorPreviousBlogsAdapter.CounselorPreviousBlogViewHolder>() {

    inner class CounselorPreviousBlogViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounselorPreviousBlogViewHolder {
        return CounselorPreviousBlogViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_counselor,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CounselorPreviousBlogViewHolder, position: Int) {
        val model = item[position]

        holder.itemView.tv_blogs_title_details.text = model.title
        holder.itemView.btn_read.setOnClickListener{
            val intent = Intent(context,ReadBlogsActivity::class.java)
            intent.putExtra(Constants.EXTRA_TITLE,model.title)
            intent.putExtra(Constants.EXTRA_ABOUT,model.description)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }
}