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
import com.sparklead.mycareer.models.GlideLoader
import com.sparklead.mycareer.models.User
import com.sparklead.mycareer.ui.activities.CounselorDetailsActivity
import kotlinx.android.synthetic.main.counselor_list_item.view.*

class CounselorDetailsAdapter(
    private val context: Context,
    private val item: ArrayList<User>
):RecyclerView.Adapter<CounselorDetailsAdapter.CounselorItemsViewHolder>() {

    inner class CounselorItemsViewHolder(view :View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CounselorItemsViewHolder {
        return CounselorItemsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.counselor_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CounselorItemsViewHolder, position: Int) {
        val model = item[position]

        GlideLoader(context).loadUserPicture(model.image,holder.itemView.iv_counselor_photo)

        holder.itemView.tv_counselor_name.text = model.name
        holder.itemView.tv_counselor_expert.text = model.fieldExpert
        holder.itemView.cv_counselor.setOnClickListener{
            val intent = Intent(context,CounselorDetailsActivity::class.java)
            intent.putExtra(Constants.COUNSELOR_ID,model.id)
            context.startActivity(intent)
        }
        holder.itemView.btn_chat.setOnClickListener {
            whatsapp(context,model.phone.toString())
        }

        if(model.rating==" "){
            holder.itemView.iv_rating.setImageResource(R.drawable.rating0)
        }
        else if(model.rating == "1"){
            holder.itemView.iv_rating.setImageResource(R.drawable.rating1)
        }
        else if (model.rating=="2"){
            holder.itemView.iv_rating.setImageResource(R.drawable.rating2)
        }
        else if(model.rating == "3"){
            holder.itemView.iv_rating.setImageResource(R.drawable.rating3)
        }
        else if(model.rating == "4"){
            holder.itemView.iv_rating.setImageResource(R.drawable.rating4)
        }
        else if(model.rating == "5"){
            holder.itemView.iv_rating.setImageResource(R.drawable.rating5)
        }
    }

    override fun getItemCount(): Int {
        return item.size

    }

    private fun whatsapp(context: Context,number:String){

        val phoneNumberWithCountryCode = "+91$number"
        val message = "Hello"

        context.startActivity(
            Intent(Intent.ACTION_VIEW,Uri.parse(String.format("https://api.whatsapp.com/send?phone=%s&text=%s", phoneNumberWithCountryCode, message))))
    }
}