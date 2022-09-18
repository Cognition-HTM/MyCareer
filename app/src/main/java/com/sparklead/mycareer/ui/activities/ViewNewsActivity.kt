package com.sparklead.mycareer.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.sparklead.mycareer.R
import com.sparklead.mycareer.models.Constants

class ViewNewsActivity : AppCompatActivity() {

    private lateinit var mContent : String
    private lateinit var mImage : String
    private lateinit var mHeading : String
    private lateinit var mUrl : String
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_news)

        supportActionBar?.hide()

        mHeading = intent.getStringExtra(Constants.HEADING).toString()
        mContent = intent.getStringExtra(Constants.CONTENT).toString()
        mImage = intent.getStringExtra(Constants.IMAGE).toString()
        mUrl = intent.getStringExtra(Constants.URL).toString()

//        webview!!.settings.javaScriptEnabled = true
//
//        webview.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
//                view?.loadUrl(mUrl)
//                return true
//            }
//        }
//        webview.loadUrl("https://www.google.co.in/")


        webView = findViewById(R.id.webview)
        webView.settings.setJavaScriptEnabled(true)

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(mUrl)
                return true
            }
        }
        webView.loadUrl(mUrl)



//        tv_heading.text = mHeading
//        tv_news_content.text = mContent
//        GlideLoader(this).loadUserPicture(mImage,iv_news_image)

    }
}