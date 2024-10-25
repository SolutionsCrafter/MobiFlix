package com.example.mobiflix

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.os.strictmode.CleartextNetworkViolation
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WebView : AppCompatActivity() {

    private lateinit var wv: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_web_view)

        initUI()
        bottomNavBar()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initUI() {

        wv = findViewById<WebView>(R.id.wv)
        val url1 = "https://web.telegram.org/a/"
        val url2 = "https://web.telegram.org/a/#2052616432"
        val url3 = "https://google.com"
        val url4 = "https://youtube.com"

        // Enable JavaScript if the website you're loading needs it
        wv.settings.javaScriptEnabled = true

        //XML WebView Approach
        wv.webViewClient = WebViewClient()// // So that links open in the WebView instead of a browser
        wv.loadUrl(url4)


        //Custom Tabs (CustomTabsIntent) Approach
        /**
        val builder = CustomTabsIntent.Builder()

        // Optionally customize the toolbar color
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.main_color))

        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))

        cl.setOnClickListener{
        recreate()
        }
         **/

    }

    private fun bottomNavBar() {

        val btnFav = findViewById<ImageView>(R.id.imgFav)
        val btnHome = findViewById<ImageView>(R.id.imgHome)
        val tvHome = findViewById<TextView>(R.id.tvHome)
        val btnFProfile = findViewById<ImageView>(R.id.imgProfile)


        btnHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        tvHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack(); // Go back to the previous page
        } else {
            super.onBackPressed(); // Exit the activity if no history
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                // Code for landscape orientation
            }
            Configuration.ORIENTATION_PORTRAIT -> {
                // Code for portrait orientation
            }
        }
    }



}