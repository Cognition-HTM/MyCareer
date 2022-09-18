package com.sparklead.mycareer.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultWithDataListener
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Order
import com.sparklead.mycareer.models.RetrofitInterface
import kotlinx.android.synthetic.main.activity_upgrade_details.*
import org.json.JSONObject
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class UpgradeDetailsActivity : AppCompatActivity() , PaymentResultWithDataListener{

    lateinit var retrofit: Retrofit
    lateinit var retrofitInterface: RetrofitInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade_details)

        Checkout.preload(applicationContext)

        val gson = GsonBuilder().setLenient()

        retrofit = Retrofit.Builder()
            .baseUrl("http://10.24.62.131:3000")
            .addConverterFactory(GsonConverterFactory.create(gson.create()))
            .build()

        retrofitInterface = retrofit.create(RetrofitInterface::class.java)

        supportActionBar?.hide()

        btn_buy.setOnClickListener {
            val amount = "400"

            getOrderId(amount)
        }
    }

    private fun getOrderId(amount : String){

        val map = HashMap<String,String>()
        map["amount"] = amount

        retrofitInterface.getOrderId(map).enqueue(object : Callback<Order> {
            override fun onResponse(call: Call<Order>, response: Response<Order>) {
                if(response.body() !=null)
                    initiatePayment(amount, response.body()!!)
                println("yessssssssssss")
            }

            override fun onFailure(call: Call<Order>, t: Throwable) {
                Toast.makeText(this@UpgradeDetailsActivity,t.message,Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun initiatePayment(amount: String, order: Order) {

        val checkout = Checkout()

        checkout.setKeyID(order.getKeyId())

        checkout.setImage(R.drawable.ic_baseline_person_24)

        val paymentOptions = JSONObject()
        paymentOptions.put("name" , "CaGui")
        paymentOptions.put("amount",amount)
        paymentOptions.put("order_id",order.getOrderId())
        paymentOptions.put("currency","INR")
        paymentOptions.put("description","Reference no : #1234")

        checkout.open(this,paymentOptions)
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {

        val map  = HashMap<String,String>()

        map["order_id"] = p1!!.orderId
        map["pay_id"] = p1.paymentId
        map["signature"] = p1.signature

        retrofitInterface.updateTransaction(map)
            .enqueue(object :Callback<String>{
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if(response.body().equals("success"))
                        Toast.makeText(this@UpgradeDetailsActivity,"Payment Success",Toast.LENGTH_SHORT).show()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(this@UpgradeDetailsActivity,t.message,Toast.LENGTH_SHORT).show()
                }

            })

    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this,p1,Toast.LENGTH_SHORT).show()
    }

}