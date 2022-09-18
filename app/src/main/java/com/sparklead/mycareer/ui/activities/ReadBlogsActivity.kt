package com.sparklead.mycareer.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import kotlinx.android.synthetic.main.activity_read_blogs.*

class ReadBlogsActivity : AppCompatActivity() {

    private lateinit var mTitle: String
    private lateinit var mDes :String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_blogs)

        mTitle = intent.getStringExtra(Constants.EXTRA_TITLE).toString()
        mDes = intent.getStringExtra(Constants.EXTRA_ABOUT).toString()

        tv_blogs_read_title.text = mTitle
        tv_blogs_read.text = mDes

    }
}