package com.sparklead.mycareer.ui.activities

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.*
import com.sparklead.mycareer.ui.adapters.CounselorPreviousBlogsAdapter
import com.sparklead.mycareer.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_counselor_details.*
import java.util.concurrent.TimeUnit

class CounselorDetailsActivity : BaseActivity() {

    private lateinit var mUserDetails :User
    private lateinit var mCounselorId : String
    private lateinit var mLinkedin :String
    private lateinit var mDescription : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counselor_details)

        supportActionBar?.hide()


        mCounselorId = intent.getStringExtra(Constants.COUNSELOR_ID).toString()

        iv_linkedin_counselor.setOnClickListener {
            getOpenLinkedin(this,mLinkedin)
        }

        btn_join.setOnClickListener {

            val userHashMap = HashMap<String,Any>()
            userHashMap[Constants.USER_POPUP] = 1

            FirestoreClass().updateUserProfileData(this@CounselorDetailsActivity,userHashMap)

            getOpenMeet(this,mDescription)
        }
    }

    override fun onResume() {
        FirestoreClass().getUserDetails(this)
        getPreviousBlogList()
        getCounselorDetails()
        getSessionsDetails()
        super.onResume()
    }

    private fun getPreviousBlogList(){

        showProgressbar(resources.getString(R.string.please_wait))

        FirestoreClass().getDetailsPreviousBlogsList(this, mCounselorId)
    }

    fun noBlogsAvailable(){

        hideProgressDialog()
    }

    fun getBlogsDetailsSuccess(blogsList : ArrayList<PreviousBlogs>){

        hideProgressDialog()

        rv_counselor_details_blog.layoutManager = LinearLayoutManager(this)
        rv_counselor_details_blog.setHasFixedSize(true)
        val adapter = CounselorPreviousBlogsAdapter(this,blogsList)
        rv_counselor_details_blog.adapter =adapter
    }

    private fun getCounselorDetails(){

        FirestoreClass().getCounselorDetails(this,mCounselorId)
    }

    fun counselorDetailsSuccess(counselor : User){

        mLinkedin = counselor.linkedin
        mUserDetails = counselor

        GlideLoader(this).loadUserPicture(counselor.image,iv_counselor_detail_photo)

        tv_counselor_detail_name.text = counselor.name
        tv_counselor_detail_about.text = counselor.about.replace("\\n","\n")
    }

    private fun getSessionsDetails(){
        FirestoreClass().getSessionsCurrentList(this,mCounselorId)
    }

    fun noSessionsAvailable(){

        tv_currentMeet.text = "No Sessions Available"
        tv_currentMeet.textAlignment = View.TEXT_ALIGNMENT_CENTER
        btn_join.visibility =View.GONE

    }

    fun getSessionsDetailsSuccess(sessionList: ArrayList<PreviousSessions>){
        val model = sessionList[0]

        val diffInMillsSeconds :Long = System.currentTimeMillis() - model.session_datetime
        val diffInHours : Long = TimeUnit.MILLISECONDS.toHours(diffInMillsSeconds)

        when{
            diffInHours < 1 ->{
                tv_currentMeet.text = model.title
                mDescription = model.description
            }
            else ->{

                tv_currentMeet.text = "No Sessions Available "
                tv_currentMeet.textAlignment = View.TEXT_ALIGNMENT_CENTER
                btn_join.visibility =View.GONE

            }
        }
    }

    private fun getOpenLinkedin(context: Context, url: String) {

        val linkedinIntent = Intent(Intent.ACTION_VIEW)
        linkedinIntent.setClassName("com.linkedin.android", "com.linkedin.android.profile.ViewProfileActivity")
        linkedinIntent.putExtra("memberId", "profile")
        linkedinIntent.setPackage("com.linkedin.android")
        try {

            context.startActivity(linkedinIntent)

        } catch (e: ActivityNotFoundException) {

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }

    private fun getOpenMeet(context: Context,url:String) {

        val meetIntent = Intent(Intent.ACTION_VIEW)
        meetIntent.setClassName("google. android. apps. meetings", "google. android. apps. meetings")
//        meetIntent.putExtra("memberId", "profile")
        meetIntent.setPackage("google. android. apps. meetings")
        try {
            context.startActivity(meetIntent)

        } catch (e: ActivityNotFoundException) {

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }

    fun userDetailsSuccess(user : User) {
        if (user.popUp == 1) {
            val dialog = BottomSheetDialog(this)
            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog, null)

//            val rBar = findViewById<RatingBar>(R.id.rBar)
//            if (rBar != null) {
            val btnSubmit = view.findViewById<Button>(R.id.btn_submit)
//                Toast.makeText(this, "Rating is: ", Toast.LENGTH_SHORT).show()
            btnSubmit.setOnClickListener {
//                    val msg = rBar.rating.toString()
//                    Toast.makeText(this, "Rating is: "+msg, Toast.LENGTH_SHORT).show()
//                    val counselorHashMap = HashMap<String,Any>()
//                    counselorHashMap[Constants.USER_RATING] = msg
//                    print(msg)
//                    FirestoreClass().updateCounselorData(this,counselorHashMap,mCounselorId)

                val userHashMap = HashMap<String, Any>()
                userHashMap[Constants.USER_POPUP] = 0

                FirestoreClass().updateUserProfileData(this@CounselorDetailsActivity, userHashMap)
                dialog.dismiss()
            }


//                val button = findViewById<Button>(R.id.button)
//                button?.setOnClickListener {
//                    Toast.makeText(this, "Rating is: "+msg, Toast.LENGTH_SHORT).show()




            dialog.setCancelable(false)
            dialog.setContentView(view)
            dialog.show()
        }
    }
}