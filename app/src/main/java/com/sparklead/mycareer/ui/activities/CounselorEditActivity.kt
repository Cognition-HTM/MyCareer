package com.sparklead.mycareer.ui.activities

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.models.PreviousBlogs
import com.sparklead.mycareer.models.PreviousSessions
import com.sparklead.mycareer.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_counselor_edit.*

class CounselorEditActivity : BaseActivity() , View.OnClickListener {

    private lateinit var mTitle : String
    private lateinit var mUserId :String
    private lateinit var mAbout : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counselor_edit)

        mTitle = intent.getStringExtra(Constants.EXTRA_TITLE_EDIT).toString()
        mUserId =intent.getStringExtra(Constants.EXTRA_COUNSELOR_ID).toString()

        if(mTitle == "Edit Biography"){
            tl_counselor_edit_title.visibility = View.GONE
            et_counselor_edit_description.hint = "About yourself"
            mAbout = intent.getStringExtra(Constants.EXTRA_ABOUT).toString()
            et_counselor_edit_description.setText(mAbout.replace("\\n","\n"))
            et_counselor_edit_description.textAlignment = View.TEXT_ALIGNMENT_CENTER
            btn_counselor_edit_biography_done.visibility=View.VISIBLE
        }
        if(mTitle == "Create New Blog"){
            btn_counselor_edit_blog_done.visibility = View.VISIBLE
        }

        if(mTitle == "Take New Session"){
            btn_counselor_edit_sessions_done.visibility = View.VISIBLE
//            et_counselor_edit_title.hint = "Session Topic"
            et_counselor_edit_description.hint = "Enter your Google Meet link "
            ll_note.visibility = View.VISIBLE
            tv_note.visibility = View.VISIBLE
        }
        if(mTitle == "Add Biography"){
            tl_counselor_edit_title.visibility = View.GONE
            et_counselor_edit_description.hint = "About yourself"
            et_counselor_edit_description.textAlignment = View.TEXT_ALIGNMENT_CENTER
            btn_counselor_edit_biography_done.visibility=View.VISIBLE
        }

        btn_counselor_edit_biography_done.setOnClickListener(this)
        btn_counselor_edit_blog_done.setOnClickListener(this)
        btn_counselor_edit_sessions_done.setOnClickListener(this)
        iv_open_meet.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v!=null) {
            when (v.id) {
                R.id.btn_counselor_edit_biography_done->{

                    showProgressbar(resources.getString(R.string.please_wait))

                    val userHashMap =HashMap<String,Any>()
                    val description = et_counselor_edit_description.text.toString().trim{ it<=' '}

                    userHashMap[Constants.ABOUT] = description

                    FirestoreClass().updateUserProfileData(this,userHashMap)
                }
                R.id.btn_counselor_edit_blog_done->{

                    showProgressbar(resources.getString(R.string.please_wait))
                    FirestoreClass().getBlogsList(this,mUserId)

                }
                R.id.btn_counselor_edit_sessions_done->{
                    showProgressbar(resources.getString(R.string.please_wait))
                    FirestoreClass().getSessionsList(this)
                }
                R.id.iv_open_meet ->{
                    getOpenMeet(this)
                }
            }
        }
    }

    fun successfullyBlogs(blogsList: ArrayList<PreviousBlogs>){

        val blogs = PreviousBlogs(

            title = et_counselor_edit_title.text.toString().trim{ it<=' ' },
            description = et_counselor_edit_description.text.toString().trim{ it<=' '}
        )
        blogsList.add(0,blogs)
        updateBlogs(blogsList)
    }

    private fun updateBlogs(blogsList: ArrayList<PreviousBlogs>) {

        val blogsHashMap =HashMap<String,Any>()

        blogsHashMap[Constants.ID] = mUserId
        blogsHashMap[Constants.ITEM] = blogsList

        FirestoreClass().updateBlogs(this,blogsHashMap)
    }

    fun blogsUpdatedSuccessfully(){
        hideProgressDialog()
        onBackPressed()
    }

    fun newUserBlogs(){
        val blogsList = ArrayList<PreviousBlogs>()

        val blogs = PreviousBlogs(
            title = et_counselor_edit_title.text.toString().trim{ it<=' ' },
            description = et_counselor_edit_description.text.toString().trim{ it<=' '}
        )

        blogsList.add(0,blogs)
        updateBlogs(blogsList)
    }

    fun updateSessionsSuccess(){
        hideProgressDialog()
        onBackPressed()
    }

    fun newUserSessions(){

        val sessionsList = ArrayList<PreviousSessions>()

        val blogs = PreviousSessions(
            title = et_counselor_edit_title.text.toString().trim{ it<=' ' },
            description ="http://${et_counselor_edit_description.text.toString().trim{ it<=' '}}",
            System.currentTimeMillis()
        )
        sessionsList.add(0,blogs)
        updateSessions(sessionsList)
    }

    private fun updateSessions(sessionsList: ArrayList<PreviousSessions>) {

        val sessionsHashMap =HashMap<String,Any>()

        sessionsHashMap[Constants.ID] = mUserId
        sessionsHashMap[Constants.ITEM] = sessionsList

        FirestoreClass().updateSessions(this,sessionsHashMap)
    }

    fun successfullySessions(sessionsList: ArrayList<PreviousSessions>){

        val sessions = PreviousSessions(
            title = et_counselor_edit_title.text.toString().trim{ it<=' ' },
            description ="http://${et_counselor_edit_description.text.toString().trim{ it<=' '}}",
            System.currentTimeMillis()

        )
        sessionsList.add(0,sessions)
        updateSessions(sessionsList)
    }

    private fun getOpenMeet(context: Context) {

        val meetIntent = Intent(Intent.ACTION_VIEW)
        meetIntent.setClassName("google. android. apps. meetings", "google. android. apps. meetings")
//        meetIntent.putExtra("memberId", "profile")
        meetIntent.setPackage("google. android. apps. meetings")
        try {
            context.startActivity(meetIntent)

        } catch (e: ActivityNotFoundException) {

            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://meet.google.com/")))
        }
    }

    fun userUpdateSuccess(){

        hideProgressDialog()
        onBackPressed()

    }
}