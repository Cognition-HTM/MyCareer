package com.sparklead.mycareer.ui.adapters

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.models.Explore
import com.sparklead.mycareer.ui.activities.ExploreDetailsActivity
import kotlinx.android.synthetic.main.explore_list_item.view.*
import java.util.zip.Inflater

class ExploreListAdapter(
    private val context: Context,
    private val item : ArrayList<Explore>
):RecyclerView.Adapter<ExploreListAdapter.ExploreListViewHolder>() {

    inner class ExploreListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExploreListViewHolder {
        return ExploreListViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.explore_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ExploreListViewHolder, position: Int) {

        val model = item[position]

        holder.itemView.tv_explore_name.text = model.title

        val uri = Uri.parse(model.image)
        holder.itemView.iv_explore_image.setImageURI(uri)

        holder.itemView.cv_explore.setOnClickListener {
            val intent = Intent(context,ExploreDetailsActivity::class.java)
            intent.putExtra(Constants.EXTRA_TITLE,model.title)
            intent.putExtra(Constants.EXTRA_IMAGE,model.image)
            intent.putExtra(Constants.EXTRA_USER_DETAILS,model.des)
            intent.putExtra(Constants.EXTRA_ARRAY,model.type)
            intent.putExtra(Constants.EXTRA_ABOUT,position.toString())
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return item.size
    }

}