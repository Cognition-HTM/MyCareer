package com.sparklead.mycareer.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import com.sparklead.mycareer.R
import com.sparklead.mycareer.ui.fragments.CounselorFragment
import com.sparklead.mycareer.ui.fragments.ExploreFragment
import com.sparklead.mycareer.ui.fragments.FAQsFragment
import com.sparklead.mycareer.ui.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : BaseActivity() {

    private var mCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        supportActionBar?.hide()

        val fragmentHome = HomeFragment()
        val fragmentProfile = ExploreFragment()
        val fragmentCounselor = CounselorFragment()
        val fragmentFaqs = FAQsFragment()

        replaceCurrentFragment(fragmentHome)
        nav_view.setItemSelected(R.id.navigation_home)


        val myBottomNavigationView = findViewById<ChipNavigationBar>(R.id.nav_view)

        myBottomNavigationView.setOnItemSelectedListener { item ->
            when (item) {
                R.id.navigation_home -> {
                    mCount=1
                    Log.i("NavBar", "Home pressed")
                    replaceCurrentFragment(fragmentHome)
                }
                R.id.navigation_explore -> {
                    mCount=0
                    Log.i("NavBar", "Profile pressed")
                    replaceCurrentFragment(fragmentProfile)
                }
                R.id.navigation_counselor -> {
                    mCount=0
                    replaceCurrentFragment(fragmentCounselor)
                }
                R.id.navigation_faqs -> {
                    mCount=0
                    replaceCurrentFragment(fragmentFaqs)
                }
                else -> {
                    Log.i("NavBar", "Error?")

                }
            }
        }
    }

    private fun replaceCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment_activity_dashboard, fragment)
            commit()
        }


    override fun onBackPressed() {
        if(mCount == 0) {
            goHomeFragment()
        }
        else{
            doubleBackToExit()
        }
        mCount++
    }

    private fun goHomeFragment(){
        val fragmentHome = HomeFragment()
//        replaceCurrentFragment(fragmentHome)
        nav_view.setItemSelected(R.id.navigation_home)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.nav_host_fragment_activity_dashboard,fragmentHome)
            commit()
        }
    }
}
