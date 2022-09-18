package com.sparklead.mycareer.ui.activities

import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.sparklead.mycareer.R
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        supportActionBar?.hide()


        btn_reset.setOnClickListener {
            val email :String =et_email_forget_password.text.toString().trim{ it<= ' '}

            if (email.isEmpty())
            {
                showErrorSnackBar(resources.getString(R.string.err_msg_email),true)
            }
            else
            {
                showProgressbar(resources.getString(R.string.please_wait))
                FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener { task->
                    hideProgressDialog()

                    if(task.isSuccessful)
                    {
                        Toast.makeText(this,"Email sent successfully to reset your password !",
                            Toast.LENGTH_LONG).show()

                        finish()
                    }
                    else
                    {
                        showErrorSnackBar(task.exception!!.message.toString(),true)
                    }
                }
            }
        }

    }

}