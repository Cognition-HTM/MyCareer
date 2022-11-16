package com.sparklead.mycareer.ui.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.*
import com.sparklead.mycareer.ui.activities.ViewNewsActivity
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(
    val context: Context
): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    private val item = ArrayList<News>()

    inner class NewsViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_news,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val model = item[position]

        holder.itemView.tv_news.text = model.title
//        holder.itemView.iv_news.
        GlideLoader(context).loadUserPicture(model.urlToImage,holder.itemView.iv_news)
        holder.itemView.cv_news.setOnClickListener{
            val intent = Intent(context,ViewNewsActivity::class.java)
            intent.putExtra(Constants.HEADING,model.title)
            intent.putExtra(Constants.CONTENT,model.content)
            intent.putExtra(Constants.IMAGE,model.urlToImage)
            intent.putExtra(Constants.URL,model.url)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return item.size
    }

    fun updateData(newData: ArrayList<News>){
        item.clear()
        item.addAll(newData)

        notifyDataSetChanged()

    }

}