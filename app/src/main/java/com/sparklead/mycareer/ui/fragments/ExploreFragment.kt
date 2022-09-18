package com.sparklead.mycareer.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.models.Explore
import com.sparklead.mycareer.ui.activities.ExploreDetailsActivity
import com.sparklead.mycareer.ui.adapters.ExploreListAdapter
import com.sparklead.mycareer.ui.adapters.NewsAdapter
import com.sparklead.mycareer.ui.firestore.FirestoreClass


class ExploreFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        activity?.window!!.statusBarColor = requireActivity().getColor(R.color.second_color)

        FirestoreClass().getExploreList(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root =inflater.inflate(R.layout.fragment_explore,container,false)

        recyclerView = root.findViewById(R.id.rv_explore)

        searchView = root.findViewById<SearchView>(R.id.sv_explore_search)

//        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//            }

//            override fun onQueryTextChange(query: String?): Boolean {
//                TODO("Not yet implemented")
//            }
//
//        })

        return root
    }

    fun getExploreSuccess(exploreList : ArrayList<Explore>){

        recyclerView.layoutManager = GridLayoutManager(activity,3)
        //
        val mAdapter  = ExploreListAdapter(requireActivity(),exploreList)
        recyclerView.adapter = mAdapter
    }
}