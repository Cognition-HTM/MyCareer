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
import com.sparklead.mycareer.models.Exams
import com.sparklead.mycareer.ui.activities.ExamPathDetailsActivity
import kotlinx.android.synthetic.main.item_exam.view.*

class ExamsAdapters (
    val context: Context,
    val item : ArrayList<Exams>
    ): RecyclerView.Adapter<ExamsAdapters.ExamsViewHolder>(){

    inner class ExamsViewHolder(itemView :View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExamsViewHolder {
        return ExamsViewHolder(LayoutInflater.from(context).inflate(
            R.layout.item_exam,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ExamsViewHolder, position: Int) {
        val model = item[position]

        val uri = Uri.parse(model.image)

        holder.itemView.tv_exam_title.text = model.title
        holder.itemView.iv_exam.setImageURI(uri)

        holder.itemView.cv_exams.setOnClickListener {
            val intent =Intent(context,ExamPathDetailsActivity::class.java)
            intent.putExtra(Constants.EXTRA_TITLE,model.title.replace("\\n","\n"))
            intent.putExtra(Constants.EXTRA_IMAGE,model.image)
            intent.putExtra(Constants.EXTRA_PRE,model.pre.replace("\\n","\n"))
            intent.putExtra(Constants.EXTRA_COLLEGE,model.college.replace("\\n","\n"))
            intent.putExtra(Constants.EXTRA_CAREER,model.career.replace("\\n","\n"))
            intent.putExtra(Constants.EXTRA_LINK,model.link)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return item.size
    }


}