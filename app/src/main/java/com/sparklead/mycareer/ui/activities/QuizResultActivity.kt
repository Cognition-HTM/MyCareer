package com.sparklead.mycareer.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.ui.firestore.FirestoreClass
import com.sparklead.mycareer.ui.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_quiz_result.*

class QuizResultActivity : BaseActivity() {

    private lateinit var mUserName : String
    private lateinit var mNumber : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_result)

        mUserName = intent.getStringExtra(Constants.NAME).toString()
        mNumber = intent.getStringExtra(Constants.ABOUT).toString()
        
        tv_result_name.text = mUserName

        if(mNumber == "1" ){
            tv_result.text = "Musical"
            tv_result1.text = "Musician"
            tv_result2.text = "Piano Teacher"
            tv_result3.text = "Conductor"
            tv_result4.text = "Composer"
            tv_result5.text = "Dance Teacher"
            tv_result6.text = "Music Therapist"
            tv_result7.text = "Choral Director"
        }
        else if(mNumber == "2"){
            tv_result.text = "Spatial"
            tv_result1.text = "Architect"
            tv_result2.text = "Geometry Teacher"
            tv_result3.text = "Photographer"
            tv_result4.text = "Surveyor"
            tv_result5.text = "Urban Planner"
            tv_result6.text = "Graphic Artist"
            tv_result7.text = "Interior Decorator"
        }
        else if(mNumber == "3"){
            tv_result.text = "Interpersonal"
            tv_result1.text = "Counselor"
            tv_result2.text = "Human Resources"
            tv_result3.text = "Manager"
            tv_result4.text = "Psychologist"
            tv_result5.text = "Public Relations"
            tv_result6.text = "Social Director"
            tv_result7.text = "Teacher"
        }
        else if(mNumber == "4"){

            tv_result.text = "Existential"
            tv_result1.text = "Inspirational Speaker"
            tv_result2.text = "Writer"
            tv_result3.text = "Clergy"
            tv_result4.text = "Author"
            tv_result5.text = "Philosopher"
            tv_result6.text = "Economist"
            tv_result7.text = "Blogger"
        }
        else if(mNumber == "5"){

            tv_result.text = "Naturalist"
            tv_result1.text = "Botanist"
            tv_result2.text = "Oceanographer"
            tv_result3.text = "Camp Counselor"
            tv_result4.text = "Scout Troop Leader"
            tv_result5.text = "Gardener"
            tv_result6.text = "Astronomer"
            tv_result7.text = "Meteorologist"
        }
        else if(mNumber == "6"){

            tv_result.text = "Linguistic"
            tv_result1.text = "Public Speaker"
            tv_result2.text = "Librarian"
            tv_result3.text = "Politics"
            tv_result4.text = "Radio announcer"
            tv_result5.text = "TV host"
            tv_result6.text = "YouTuber"
            tv_result7.text = "Journalist"
        }
        else if(mNumber == "7"){

            tv_result.text = "Bodily-Kinesthetic "
            tv_result1.text = "Physical Therapist"
            tv_result2.text = "Dancer"
            tv_result3.text = "Athlete"
            tv_result4.text = "Coach"
            tv_result5.text = "Fitness"
            tv_result6.text = "Gym Owner"
            tv_result7.text = "Actor"

        }
        else if(mNumber == "8"){

            tv_result.text = "Logical-Mathematical"
            tv_result1.text = "Engineering"
            tv_result2.text = "Mathematician"
            tv_result3.text = "Economist"
            tv_result4.text = "Auditor"
            tv_result5.text = "Accountant"
            tv_result6.text = "Scientist"
            tv_result7.text = "Statistician"
        }
        else if(mNumber == "9"){

            tv_result.text = "Intra-personal"
            tv_result1.text = "Psychologist"
            tv_result2.text = "Writer"
            tv_result3.text = "Therapist"
            tv_result4.text = "Counselor"
            tv_result5.text = "Social Worker"
            tv_result6.text = "Theologian"
            tv_result7.text = "Entrepreneur"

        }

        btn_go_to_home.setOnClickListener{

            val userHashMap = HashMap<String,Any>()
            userHashMap[Constants.RESULT] = tv_result.text
            FirestoreClass().updateUserProfileData(this@QuizResultActivity,userHashMap)

            startActivity(Intent(this,DashboardActivity::class.java))
            finish()
        }

    }
}