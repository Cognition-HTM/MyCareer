package com.sparklead.mycareer.ui.activities

import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.models.Details
import com.sparklead.mycareer.ui.adapters.ExploreDetailsAdapter
import com.sparklead.mycareer.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_explore_details.*

class ExploreDetailsActivity : BaseActivity() {

    private lateinit var mTitle : String
    private lateinit var mDetails :String
    private lateinit var mImage : String
    private lateinit var mDes : String
    private lateinit var mArray: ArrayList<Details>
    private lateinit var i : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explore_details)

        supportActionBar?.hide()

        mTitle = intent.getStringExtra(Constants.EXTRA_TITLE)!!
        mImage = intent.getStringExtra(Constants.EXTRA_IMAGE)!!
        mDes = intent.getStringExtra(Constants.EXTRA_USER_DETAILS)!!
        i = intent.getStringExtra(Constants.EXTRA_ABOUT)!!
        mArray = intent.getParcelableArrayListExtra<Details>(Constants.EXTRA_ARRAY)!!

        i.toInt()

        val uri = Uri.parse(mImage)
        iv_branch_explore_image.setImageURI(uri)

        tv_title_explore_details.text = mTitle
        tv_title_heading.text = mTitle

        tv_title_explore_details_details.text = mDes

        populateListUI(mArray)
    }

    private fun populateListUI(branchItem: ArrayList<Details>){

        rv_branch_list.layoutManager = LinearLayoutManager(this)
        rv_branch_list.setHasFixedSize(true)

        val exploreDetailsAdapter = ExploreDetailsAdapter(this,branchItem,mImage)
        rv_branch_list.adapter = exploreDetailsAdapter
    }
}