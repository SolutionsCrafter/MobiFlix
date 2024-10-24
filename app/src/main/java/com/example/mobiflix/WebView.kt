package com.example.mobiflix

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.strictmode.CleartextNetworkViolation
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.ScrollView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class WebView : AppCompatActivity() {
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

    private fun initUI(){

        val cl = findViewById<ConstraintLayout>(R.id.cl)

        val url = "https://t.me/NetflixBot"
        val builder = CustomTabsIntent.Builder()

        // Optionally customize the toolbar color
        builder.setToolbarColor(ContextCompat.getColor(this, R.color.main_color))

        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))

        cl.setOnClickListener{
            recreate()
        }

    }

    private fun bottomNavBar() {

        val btnExplorer = findViewById<ImageView>(R.id.imgExplo)
        val btnFav = findViewById<ImageView>(R.id.imgFav)
        val btnHome = findViewById<ImageView>(R.id.imgHome)
        val btnFProfile = findViewById<ImageView>(R.id.imgProfile)


        btnHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


}