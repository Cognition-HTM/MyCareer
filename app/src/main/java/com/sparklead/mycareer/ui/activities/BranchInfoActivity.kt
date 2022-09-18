package com.sparklead.mycareer.ui.activities

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.models.Favourite
import com.sparklead.mycareer.ui.adapters.BranchInfoAdapter
import com.sparklead.mycareer.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_branch_info.*

class BranchInfoActivity : BaseActivity() {

    private lateinit var mSkill : String
    private lateinit var mDes : String
    private lateinit var mTitle : String
    private var mVisible : String = "false"
    private lateinit var mImage : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_branch_info)

        supportActionBar?.hide()

        mTitle = intent.getStringExtra(Constants.EXTRA_TITLE).toString()
        mDes = intent.getStringExtra(Constants.EXTRA_USER_DETAILS).toString()
        mSkill = intent.getStringExtra(Constants.EXTRA_ABOUT).toString()
        mImage = intent.getStringExtra(Constants.EXTRA_IMAGE).toString()


        tv_title_explore_details_info.text = mTitle

        populateListUI(mDes,mSkill)

//        iv_add_favourite.setOnClickListener {
//
//            if(mVisible == "true"){
//                iv_add_favourite.setImageDrawable(resources.getDrawable(R.drawable.like))
//            }
//            else{
//                FirestoreClass().getFavouriteList(this)
//            }

//            visible =! visible
//
//            if(visible){
//                iv_add_favourite.setImageDrawable(resources.getDrawable(R.drawable.unlike))

//            }
//            else{
//                iv_add_favourite.setImageDrawable(resources.getDrawable(R.drawable.like))
//            }
//        }

    }

    override fun onResume() {
        FirestoreClass().getVisibleFavouriteList(this)
        super.onResume()
    }

    private fun populateListUI(des:String, skill:String){

        rv_details_info.layoutManager = LinearLayoutManager(this)
        rv_details_info.setHasFixedSize(true)

        val branchInfoAdapter = BranchInfoAdapter(this,des,skill)
        rv_details_info.adapter = branchInfoAdapter
    }

    private fun updateFavouriteList(favouriteList : ArrayList<Favourite>){

        val favouriteHashMap = HashMap<String,Any>()
        favouriteHashMap[Constants.ITEM] = favouriteList

        FirestoreClass().updateFavourite(this,favouriteHashMap)
    }

    fun updateFavouriteSuccess(){
//        hideProgressDialog()
        onBackPressed()
    }

    fun newUserFavoriteSuccess(){

        val favouriteList = ArrayList<Favourite>()

        val favourite = Favourite(
            image  = mImage,
            title = mTitle,
            des = mDes,
            skill = mSkill
        )
        favouriteList.add(0,favourite)
        updateFavouriteList(favouriteList)
    }

    fun successfullyFavourite(favouriteList : ArrayList<Favourite>){

        val favourite = Favourite(
            image  = mImage,
            title = mTitle,
            des = mDes,
            skill = mSkill
        )
        favouriteList.add(0,favourite)
        updateFavouriteList(favouriteList)
    }

    fun visibleSuccess(favouriteList : ArrayList<Favourite>){
        for(i in favouriteList){
            if(i.title==mTitle){
                mVisible = "true"
            }
        }
        if(mVisible == "true"){
            iv_delete_favourite.visibility = View.VISIBLE
            iv_delete_favourite.setOnClickListener {
                FirestoreClass().deleteListCheckFavorite(this)
            }
        }
        else{
            iv_add_favourite.visibility = View.VISIBLE
            iv_add_favourite.setOnClickListener {
                FirestoreClass().getFavouriteList(this)
            }
        }

    }

    fun firstUserFavorite(){
        iv_add_favourite.visibility = View.VISIBLE
        iv_add_favourite.setOnClickListener {
            FirestoreClass().getFavouriteList(this)
        }
    }

    fun deleteListCheckSuccess(favouriteList : ArrayList<Favourite>){

        val newFavouriteList = ArrayList<Favourite>()

        for(i in favouriteList){
            if(i.title != mTitle){
                newFavouriteList.add(i)
            }
        }
        updateFavouriteList(newFavouriteList)
    }
    fun deleteListCheckSuccessNew(){
        FirestoreClass().deleteFavourite(this)
    }

}