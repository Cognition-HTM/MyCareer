package com.sparklead.mycareer.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.models.GlideLoader
import com.sparklead.mycareer.models.User
import com.sparklead.mycareer.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mUserDetails : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)


//        supportActionBar?.hide()

        setupActionBar()

        tv_edit.setOnClickListener(this)
        btn_logout.setOnClickListener(this)

    }

    private fun setupActionBar(){

        setSupportActionBar(toolbar_setting)

        val actionBar = supportActionBar
        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_new_24)
        }

        toolbar_setting.setNavigationOnClickListener{ onBackPressed() }
    }

    override fun onClick(v: View?) {
        if(v!=null)
        {
            when(v.id) {
                R.id.tv_edit ->
                {
                    val intent = Intent(this, ProfileActivity::class.java)
                    intent.putExtra(Constants.EXTRA_USER_DETAILS,mUserDetails)
                    startActivity(intent)
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
                }

                R.id.btn_logout ->
                {
                    val userHashMap = HashMap<String,Any>()
                    userHashMap[Constants.USER_LOGIN] = 0
                    FirestoreClass().updateUserProfileData(this@SettingActivity,userHashMap)

                    FirebaseAuth.getInstance().signOut()
                    val intent =Intent(this,SignInActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                    finish()
                }
            }
        }

    }

    fun userUpdateSuccess(){
        hideProgressDialog()
    }

    private fun getUserDetails()
    {
        showProgressbar(resources.getString(R.string.please_wait))
        FirestoreClass().getUserDetails(this)
    }

    fun userDetailsSuccess(user: User)
    {
        mUserDetails = user

        hideProgressDialog()

        GlideLoader(this).loadUserPicture(user.image,user_image_setting1)
        tv_name.text = "Hi, ${user.name}"
        tv_email.text = user.email
    }

    override fun onResume() {
        super.onResume()
        getUserDetails()
    }
}