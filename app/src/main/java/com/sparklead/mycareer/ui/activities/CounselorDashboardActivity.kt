package com.sparklead.mycareer.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.*
import com.sparklead.mycareer.ui.adapters.PreviousBlogsAdapter
import com.sparklead.mycareer.ui.adapters.PreviousSessionsAdapter
import com.sparklead.mycareer.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_counselor_dashboard.*

class CounselorDashboardActivity : BaseActivity() {

    private lateinit var mUserDetails : User
    private lateinit var mUserId : String
    private lateinit var mAbout : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counselor_dashboard)

        iv_counselor_about_edit.setOnClickListener {
            val intent = Intent(this,CounselorEditActivity::class.java)
            intent.putExtra(Constants.EXTRA_TITLE_EDIT,"Edit Biography")
            intent.putExtra(Constants.EXTRA_COUNSELOR_ID,mUserId)
            intent.putExtra(Constants.EXTRA_ABOUT,mAbout)
            startActivity(intent)
        }
        ll_create_blogs.setOnClickListener {
            val intent = Intent(this,CounselorEditActivity::class.java)
            intent.putExtra(Constants.EXTRA_TITLE_EDIT,"Create New Blog")
            intent.putExtra(Constants.EXTRA_COUNSELOR_ID,mUserId)
            startActivity(intent)
        }
        ll_live_sessions.setOnClickListener {
            val intent = Intent(this,CounselorEditActivity::class.java)
            intent.putExtra(Constants.EXTRA_TITLE_EDIT,"Take New Session")
            intent.putExtra(Constants.EXTRA_COUNSELOR_ID,mUserId)
            startActivity(intent)
        }
        ll_add_about.setOnClickListener{
            val intent = Intent(this,CounselorEditActivity::class.java)
            intent.putExtra(Constants.EXTRA_TITLE_EDIT,"Add Biography")
            intent.putExtra(Constants.EXTRA_COUNSELOR_ID,mUserId)
            startActivity(intent)
        }



    }

    override fun onResume() {
        getUserDetails()
        getPreviousBlogList()
        getPreviousSessionsList()
        super.onResume()
    }

    private fun getUserDetails()
    {
        showProgressbar(resources.getString(R.string.please_wait))
        FirestoreClass().getUserDetails(this)
    }

    fun userDetailsSuccess(user: User){

        mUserDetails = user
        mUserId = user.id


        if(user.rating==" "){
            iv_rating_counselor_dashboard.setImageResource(R.drawable.rating0)
        }
        else if(user.rating == "1"){
            iv_rating_counselor_dashboard.setImageResource(R.drawable.rating1)
        }
        else if (user.rating=="2"){
            iv_rating_counselor_dashboard.setImageResource(R.drawable.rating2)
        }
        else if(user.rating == "3"){
            iv_rating_counselor_dashboard.setImageResource(R.drawable.rating3)
        }
        else if(user.rating == "4"){
            iv_rating_counselor_dashboard.setImageResource(R.drawable.rating4)
        }
        else if(user.rating == "5"){
            iv_rating_counselor_dashboard.setImageResource(R.drawable.rating5)
        }

        if(user.about == " "){
            ll_add_about.visibility = View.VISIBLE
            iv_counselor_about_edit.visibility =View.GONE
        }
        else{
            ll_add_about.visibility = View.GONE
            iv_counselor_about_edit.visibility =View.VISIBLE
            tv_counselor_about.visibility =View.VISIBLE
            tv_counselor_about.text = user.about.replace("\\n","\n")
            mAbout = user.about
        }

        hideProgressDialog()

        GlideLoader(this).loadUserPicture(user.image,user_image_counselor)

        tv_counselor_dashboard_name.text = "Hi, ${user.name}"

        user_image_counselor.setOnClickListener {
            startActivity(Intent(this,SettingActivity::class.java))
        }

    }

    private fun getPreviousBlogList(){
        FirestoreClass().getPreviousBlogsList(this)
    }

    fun getBlogsDetailsSuccess(blogsList : ArrayList<PreviousBlogs>){

        hideProgressDialog()

        tv_blogs_status.text = "Previous Blogs"

        rv_previous_blogs.layoutManager = LinearLayoutManager(this)
        rv_previous_blogs.setHasFixedSize(true)
        val adapter = PreviousBlogsAdapter(this,blogsList)
        rv_previous_blogs.adapter =adapter

    }

    fun noBlogsAvailable(){
        hideProgressDialog()

        tv_blogs_status.text = "No Previous Blogs"

    }

    fun getSessionsDetailsSuccess(sessionsList : ArrayList<PreviousSessions>){

        hideProgressDialog()

        tv_previous_sessions_status.text = "Previous Sessions"

        rv_previous_sessions.layoutManager = LinearLayoutManager(this)
        rv_previous_sessions.setHasFixedSize(true)
        val adapter = PreviousSessionsAdapter(this,sessionsList)
        rv_previous_sessions.adapter =adapter

    }


    private fun getPreviousSessionsList(){
        FirestoreClass().getPreviousSessionsList(this)
    }

    fun noSessionsAvailable(){
        hideProgressDialog()

        tv_previous_sessions_status.text = "No Previous Sessions"
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}