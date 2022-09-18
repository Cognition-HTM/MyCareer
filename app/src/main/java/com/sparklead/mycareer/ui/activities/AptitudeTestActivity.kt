package com.sparklead.mycareer.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.models.Exams
import com.sparklead.mycareer.ui.adapters.ExamsAdapters
import com.sparklead.mycareer.ui.firestore.FirestoreClass
import kotlinx.android.synthetic.main.activity_aptitude_test.*
import java.util.ArrayList

class AptitudeTestActivity : AppCompatActivity() {

    private lateinit var mUserName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aptitude_test)

        supportActionBar?.hide()

        mUserName = intent.getStringExtra(Constants.NAME).toString()

        btn_quiz.setOnClickListener{
            val intent = Intent(this,QuizActivity::class.java)
            intent.putExtra(Constants.NAME,mUserName)
            startActivity(intent)
            finish()
        }
    }
}