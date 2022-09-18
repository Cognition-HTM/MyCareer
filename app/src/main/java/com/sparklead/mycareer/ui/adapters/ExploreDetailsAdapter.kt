package com.sparklead.mycareer.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.models.Details
import com.sparklead.mycareer.ui.activities.BranchInfoActivity
import kotlinx.android.synthetic.main.item_list_type_branches.view.*

class ExploreDetailsAdapter(
    private val context: Context,
    private val list: ArrayList<Details>,
    private val mImage: String,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_list_type_branches,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

            holder.itemView.tv_branch_name.text = model.name

            holder.itemView.btn_view_details.setOnClickListener {
                val intent = Intent(context,BranchInfoActivity::class.java)
                intent.putExtra(Constants.EXTRA_TITLE,model.name)
                intent.putExtra(Constants.EXTRA_USER_DETAILS,model.des)
                intent.putExtra(Constants.EXTRA_ABOUT,model.future)
                intent.putExtra(Constants.EXTRA_IMAGE,mImage)
                context.startActivity(intent)
            }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view)
}