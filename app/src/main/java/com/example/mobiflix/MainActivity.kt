package com.example.mobiflix

import android.app.DownloadManager
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.speech.RecognizerIntent
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.util.Locale
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.privacysandbox.tools.core.model.Method
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.mobiflix.Activities.DetailActivity
import com.example.mobiflix.Adapters.CategoryListAdapter
import com.example.mobiflix.Adapters.FilmListAdapter
import com.example.mobiflix.Domains.GenresItem
import com.example.mobiflix.Domains.ListFilm
import com.example.mobiflix.animations.AnimationTypes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Objects
import java.util.logging.Handler





class MainActivity : AppCompatActivity() {

    private lateinit var searchBar: EditText
    private lateinit var imgExplorer: LinearLayout
    private lateinit var recyclerViewBestMovies: RecyclerView
    private lateinit var recyclerViewUpcomingMovies: RecyclerView
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var loading1: ProgressBar
    private lateinit var loading2: ProgressBar
    private lateinit var loading3: ProgressBar
    private lateinit var adapterBestMovies: RecyclerView.Adapter<*>
    private lateinit var adapterUpcomingMovies: RecyclerView.Adapter<*>
    private lateinit var adapterCategory: RecyclerView.Adapter<*>

    private val slideHandler = android.os.Handler()
    private val requestQueue by lazy { Volley.newRequestQueue(this) }
    private val gson = Gson()

    private lateinit var mRequestQueue: RequestQueue
    private var mStringRequest1: StringRequest? = null
    private var mStringRequest2: StringRequest? = null
    private var mStringRequest3: StringRequest? = null


    private lateinit var etSearchBar: EditText
    private lateinit var btnMick: ImageView

    // on below line we are creating a constant value
    private val REQUEST_CODE_SPEECH_INPUT = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initUI()
        imageSlider()
        voiceSearch()

        sendRequestBestMovies()
        sendRequestUpcomingMovies()
        sendRequestCategory()

        imgExplorer.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initUI() {

        val imgSlider = findViewById<ImageSlider>(R.id.image_slider)
        etSearchBar = findViewById<EditText>(R.id.searchBar)

        imgExplorer = findViewById(R.id.ImgExplorer)

        imgExplorer.setOnClickListener {
            startActivity(Intent(this, DetailActivity::class.java))
        }

        recyclerViewBestMovies = findViewById<RecyclerView>(R.id.view1).apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
        recyclerViewCategory = findViewById<RecyclerView>(R.id.view2).apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }
        recyclerViewUpcomingMovies = findViewById<RecyclerView>(R.id.view3).apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
        }

        loading1 = findViewById(R.id.progressBar1)
        loading2 = findViewById(R.id.progressBar2)
        loading3 = findViewById(R.id.progressBar3)
    }


    private fun imageSlider() {
        val imageList = ArrayList<SlideModel>() // Create image list

        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)

// imageList.add(SlideModel("String Url" or R.drawable)
// imageList.add(SlideModel("String Url" or R.drawable, "title") You can add title

        imageList.add(SlideModel("https://occ-0-8177-64.1.nflxso.net/dnm/api/v6/Qs00mKCpRvrkl3HZAN5KwEL1kpE/AAAABYLtojOWF5eKEyoZcR47nnCA9wCH59GiNXFZpiNJwBLLjIN_xu40n9wPl6Dz4rBthp9O6NNkKP_Xn65vVoC77oy0RvfBAP5Q80g7CyYWmubF5SxNjNDYH2BkNpIw8z5qbnBw4Q.jpg?r=af1"))
        imageList.add(SlideModel("https://prod-ripcut-delivery.disney-plus.net/v1/variant/disney/DB176BD1488D7E4822256EF1778C124FC17388FC1E7F0F6D89B38AFF5FB001F6/scale?width=1200&amp;aspectRatio=1.78&amp;format=webp"))
        imageList.add(SlideModel("https://ntvb.tmsimg.com/assets/p8696131_b_h10_aa.jpg?w=960&h=540"))
        imageList.add(SlideModel("https://wallpaper.forfun.com/fetch/1d/1de7fa9a4528b8117e0ec2503afcb603.jpeg"))
        imageList.add(SlideModel("https://i0.wp.com/www.murphysmultiverse.com/wp-content/uploads/2022/12/Across-the-Spider-Verse.jpg?fit=1024%2C576&ssl=1"))
        imageList.add(SlideModel("https://i.pinimg.com/originals/b8/ee/9c/b8ee9c7cf0b95d728bf74f11df5e45cd.jpg"))
        imageList.add(SlideModel("https://w0.peakpx.com/wallpaper/593/742/HD-wallpaper-avatar-2-the-way-of-water-banner.jpg"))


        imageSlider.setImageList(imageList)
        imageSlider.setSlideAnimation(com.denzcoskun.imageslider.constants.AnimationTypes.DEPTH_SLIDE)
    }

    private fun voiceSearch() {
        btnMick = findViewById(R.id.btnMick)

        btnMick.setOnClickListener {
            // on below line we are calling speech recognizer intent.
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            // on below line we are passing language model
            // and model free form in our intent
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )

            // on below line we are passing our
            // language as a default language.
            intent.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault()
            )

            // on below line we are specifying a prompt
            // message as speak to text on below line.
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")

            // on below line we are specifying a try catch block.
            // in this block we are calling a start activity
            // for result method and passing our result code.
            try {
                startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
            } catch (e: Exception) {
                // on below line we are displaying error message in toast
                Toast
                    .makeText(
                        this@MainActivity, " " + e.message,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }
        }

    }

    // on below line we are calling on activity result method.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // in this method we are checking request
        // code with our result code.
        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
            // on below line we are checking if result code is ok
            if (resultCode == RESULT_OK && data != null) {

                // in that case we are extracting the
                // data from our array list
                val res: ArrayList<String> =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                // on below line we are setting data
                // to our output text view.
                etSearchBar.setText(
                    Objects.requireNonNull(res)[0]
                )
            }
        }
    }

    private fun sendRequestBestMovies() {
        loading1.visibility = View.VISIBLE
        val url = "https://moviesapi.ir/api/v1/movies?page=1"
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            loading1.visibility = View.GONE
            val items = gson.fromJson(response, ListFilm::class.java)
            adapterBestMovies = FilmListAdapter(items)
            recyclerViewBestMovies.adapter = adapterBestMovies
        }, { error ->
            loading1.visibility = View.GONE
            Log.i("CBROXX", "onErrorResponse: $error")
        })
        requestQueue.add(stringRequest)
    }

    private fun sendRequestUpcomingMovies() {
        loading3.visibility = View.VISIBLE
        val url = "https://moviesapi.ir/api/v1/movies?page=2"
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            loading3.visibility = View.GONE
            val items = gson.fromJson(response, ListFilm::class.java)
            adapterUpcomingMovies = FilmListAdapter(items)
            recyclerViewUpcomingMovies.adapter = adapterUpcomingMovies
        }, { error ->
            loading3.visibility = View.GONE
            Log.i("CBROXX", "onErrorResponse: $error")
        })
        requestQueue.add(stringRequest)
    }

    private fun sendRequestCategory() {
        loading2.visibility = View.VISIBLE
        val url = "https://moviesapi.ir/api/v1/genres"
        val stringRequest = StringRequest(Request.Method.GET, url, { response ->
            loading2.visibility = View.GONE
            val catlist = gson.fromJson<ArrayList<GenresItem>>(
                response,
                object : TypeToken<ArrayList<GenresItem>>() {}.type
            )
            adapterCategory = CategoryListAdapter(catlist)
            recyclerViewCategory.adapter = adapterCategory
        }, { error ->
            loading2.visibility = View.GONE
            Log.i("CBROXX", "onErrorResponse: $error")
        })
        requestQueue.add(stringRequest)
    }


}