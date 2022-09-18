package com.sparklead.mycareer.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.auth.User
import com.sparklead.mycareer.R
import com.sparklead.mycareer.ui.adapters.CounselorDetailsAdapter
import com.sparklead.mycareer.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.fragment_counselor.*

class CounselorFragment : BaseFragment() {

    private lateinit var mUserDetails :User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


        activity?.window!!.statusBarColor = requireActivity().getColor(R.color.first_color)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_counselor, container, false)

        return root
    }

    private fun getCounselorDetails(){
        showProgressDialog(resources.getString(R.string.please_wait))
        FirestoreClass().getCounselorDetailsForFragment(this)
    }

    fun getCounselorDetailsSuccess(counselorListItem: ArrayList<com.sparklead.mycareer.models.User>){
        hideProgressDialog()

        rv_counselor.layoutManager = LinearLayoutManager(activity)
        rv_counselor.setHasFixedSize(true)

        val adapter = CounselorDetailsAdapter(requireActivity(),counselorListItem)
        rv_counselor.adapter =adapter

        println("checking recycle view")
    }

    override fun onResume() {
        super.onResume()
        getCounselorDetails()
    }

}