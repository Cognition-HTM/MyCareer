package com.sparklead.mycareer.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.models.Favourite
import com.sparklead.mycareer.models.GlideLoader
import com.sparklead.mycareer.ui.activities.BranchInfoActivity
import kotlinx.android.synthetic.main.item_favourite.view.*

class FavouriteAdapter(
    val context: Context,
    val item : ArrayList<Favourite>
):RecyclerView.Adapter<FavouriteAdapter.FavouriteAdapterHolder>() {

    inner class FavouriteAdapterHolder(view : View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteAdapterHolder {
        return FavouriteAdapterHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_favourite,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: FavouriteAdapterHolder, position: Int) {
        val model = item[position]

        holder.itemView.tv_favourite.text = model.title
        GlideLoader(context).loadUserPicture(model.image,holder.itemView.iv_favourite)

        holder.itemView.cv_favourite.setOnClickListener{
            val intent = Intent(context,BranchInfoActivity::class.java)
            intent.putExtra(Constants.EXTRA_TITLE,model.title)
            intent.putExtra(Constants.EXTRA_USER_DETAILS,model.des)
            intent.putExtra(Constants.EXTRA_ABOUT,model.skill)
            intent.putExtra(Constants.EXTRA_IMAGE,model.image)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }

}