package com.sparklead.mycareer.ui.activities

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import kotlinx.android.synthetic.main.activity_exam_path_details.*

class ExamPathDetailsActivity : AppCompatActivity() {

    private lateinit var mTitle :String
    private lateinit var mImage: String
    private lateinit var mCareer :String
    private lateinit var mCollege :String
    private lateinit var mPre :String
    private lateinit var mLink :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exam_path_details)

        supportActionBar?.hide()
        mTitle = intent.getStringExtra(Constants.EXTRA_TITLE).toString()
        mImage = intent.getStringExtra(Constants.EXTRA_IMAGE).toString()
        mCareer = intent.getStringExtra(Constants.EXTRA_CAREER).toString()
        mCollege = intent.getStringExtra(Constants.EXTRA_COLLEGE).toString()
        mPre = intent.getStringExtra(Constants.EXTRA_PRE).toString()
        mLink = intent.getStringExtra(Constants.EXTRA_LINK).toString()

        tv_exam_title.text = mTitle
        tv_exam_pre.text = mPre
        tv_exam_college.text = mCollege
        tv_exam_career.text = mCareer

        btn_apply.setOnClickListener{
            applyNow(this,mLink)
        }
    }

    private fun applyNow(context: Context,url : String){
        val chromeLink = Intent(Intent.ACTION_VIEW)
        chromeLink.setClassName("com.android.chrome", "com.android.chrome")
//        meetIntent.putExtra("memberId", "profile")
        chromeLink.setPackage("com.android.chrome")
        try {
            context.startActivity(chromeLink)

        } catch (e: ActivityNotFoundException) {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }
}