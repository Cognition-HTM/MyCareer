package com.sparklead.mycareer.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.*
import com.sparklead.mycareer.ui.activities.AptitudeTestActivity
import com.sparklead.mycareer.ui.activities.SettingActivity
import com.sparklead.mycareer.ui.activities.UpgradeDetailsActivity
import com.sparklead.mycareer.ui.adapters.FavouriteAdapter
import com.sparklead.mycareer.ui.adapters.NewsAdapter
import com.sparklead.mycareer.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class HomeFragment : BaseFragment() {

    private lateinit var mUserDetails :User
    private lateinit var mAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        activity?.window!!.statusBarColor = requireActivity().getColor(R.color.third_color)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView : RecyclerView = root.findViewById(R.id.rv_news)
        recyclerView.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        mAdapter  = NewsAdapter(requireActivity())
        recyclerView.adapter = mAdapter


        val iv = root.findViewById<ImageView>(R.id.iv_upgrade_account)
        iv.setOnClickListener {
            val intent = Intent(context,UpgradeDetailsActivity::class.java)
            startActivity(intent)
        }

        val cv1 = root.findViewById<CardView>(R.id.cv_test)
        cv1.setOnClickListener {
            val intent = Intent(context,AptitudeTestActivity::class.java)
            intent.putExtra(Constants.NAME,mUserDetails.name)
            startActivity(intent)
        }

        return root
    }

    private fun getUserDetails()
    {
        FirestoreClass().getUserDetailsForFragment(this)
    }

    fun userDetailsSuccess(user: User){

        mUserDetails = user
        tv_user_name.text = "Hi, ${user.name}"

        tv_result_home.text = user.result

//        if(user.popUp == 1){
//            val dialog = BottomSheetDialog(requireActivity())
//            val view = layoutInflater.inflate(R.layout.bottom_sheet_dialog,null)
//            dialog.setCancelable(false)
//            dialog.setContentView(view)
//            dialog.show()
//        }

//        hideProgressDialog()
        GlideLoader(requireActivity()).loadUserPicture(user.image,user_image_home)

        user_image_home.setOnClickListener {
            startActivity(Intent(context,SettingActivity::class.java))
        }
    }

    override fun onResume() {
        showProgressDialog(resources.getString(R.string.please_wait))
        getUserDetails()
        FirestoreClass().getFavouriteListFragment(this)
        fetchData()
        super.onResume()
    }

    fun getFavouriteListSuccess(favouriteListItem: ArrayList<Favourite>){

        hideProgressDialog()

        if(favouriteListItem.size == 0 ){
            tv_favourite_empty.visibility = View.VISIBLE
        }

        rv_favourite.layoutManager = LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        rv_favourite.setHasFixedSize(true)
        val adapter = FavouriteAdapter(requireActivity(),favouriteListItem)
        rv_favourite.adapter =adapter

        println("checking favourite recycle view")
    }

    private fun fetchData() {

        val queue = Volley.newRequestQueue(context)
        val url =
            "https://newsapi.org/v2/everything?q=cbse&language=en&from=2022-11-15to=2022-11-16&apiKey=5c62b32d818e424797a8571a4f5bd626"

        val jsonObjectRequest = object : JsonObjectRequest(Request.Method.GET, url, null,

            { response ->
                val newsJsonArray = response.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = News(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage"),
                        newsJsonObject.getString("content")
                    )
                    newsArray.add(news)
                }

                mAdapter.updateData(newsArray)

                hideProgressDialog()
            },
            { _ ->
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String>? {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }
        queue.add(jsonObjectRequest)
    }

}