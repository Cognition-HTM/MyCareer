package com.sparklead.mycareer.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants
import com.sparklead.mycareer.models.User
import com.sparklead.mycareer.ui.firestore.FirestoreClass


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        supportActionBar?.hide()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )


        val linear = findViewById<LinearLayout>(R.id.background_splash_screen)
        val animationDrawable = linear.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(2000)
        animationDrawable.setExitFadeDuration(2000)
        animationDrawable.start()

        val value = FirestoreClass().getCurrentUserIdNo(this)
        println("check")
        println(value)
        if (value == "") {
            Handler(Looper.getMainLooper()).postDelayed({
                startActivity(Intent(this, SignInActivity::class.java))
                finish()

            }, 3000)
        } else {
            FirestoreClass().getUserDetails(this)
        }

//        Handler(Looper.getMainLooper()).postDelayed({
//
//            startActivity(Intent(this, DashboardActivity::class.java))
//            finish()
//
//        }, 3000)
//    }
    }


    fun userDetailsSuccess(user: User) {
//        if (user.login == 0) {
//            Handler(Looper.getMainLooper()).postDelayed({
//                val intent = Intent(this, SignInActivity::class.java)
//                intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
//                startActivity(intent)
//                finish()
//                                                        }
//                , 3000)
//            } else {
//                Handler(Looper.getMainLooper()).postDelayed({
//                    val intent = Intent(this, DashboardActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }, 3000)
//            }

            if (user.userType == "Student") {
                if (user.login == 0) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, SignInActivity::class.java)
                        intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
                        startActivity(intent)
                        finish()
                    }, 3000)
                } else {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, DashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 3000)
                }
            } else {
                if (user.login == 0) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, SignInActivity::class.java)
                        intent.putExtra(Constants.EXTRA_USER_DETAILS, user)
                        startActivity(intent)
                        finish()
                    }, 3000)
                } else {
                    Handler(Looper.getMainLooper()).postDelayed({
                        val intent = Intent(this, CounselorDashboardActivity::class.java)
                        startActivity(intent)
                        finish()
                    }, 3000)
                }
            }
        }
//        fun reLogin() {
//            Handler(Looper.getMainLooper()).postDelayed({
//                val intent = Intent(this, SignInActivity::class.java)
//                startActivity(intent)
//                finish()
//            }, 3000)
//        }
    }

