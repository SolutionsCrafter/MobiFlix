package com.example.mobiflix.Activities

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.mobiflix.Adapters.ActorsListAdapter
import com.example.mobiflix.Adapters.CategoryEachFilmListAdapter
import com.example.mobiflix.Domains.FilmItem
import com.example.mobiflix.R
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {

    private lateinit var mRequestQueue: RequestQueue
    private lateinit var progressBar: ProgressBar
    private lateinit var titleTxt: TextView
    private lateinit var movieRateTxt: TextView
    private lateinit var movieTimeTxt: TextView
    private lateinit var movieSummaryInfo: TextView
    private lateinit var movieActorsInfo: TextView
    private var idFilm: Int = 0
    private lateinit var pic2: ImageView
    private lateinit var backImg: ImageView
    private lateinit var recyclerViewActors: RecyclerView
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var scrollViewDetails: NestedScrollView
    private var adapterActorList: RecyclerView.Adapter<*>? = null
    private var adapterCategory: RecyclerView.Adapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        idFilm = intent.getIntExtra("id", 0)
        initView()
        sendRequest()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun sendRequest() {
        mRequestQueue = Volley.newRequestQueue(this)
        showLoading(true)

        val url = "https://moviesapi.ir/api/v1/movies/$idFilm"
        val mStringRequest = StringRequest(Request.Method.GET, url, { response ->
            showLoading(false)

            val gson = Gson()
            val item = gson.fromJson(response, FilmItem::class.java)

            Glide.with(this)
                .load(item.poster)
                .into(pic2)

            titleTxt.text = item.title
            movieRateTxt.text = item.imdbRating
            movieTimeTxt.text = item.runtime
            movieSummaryInfo.text = item.plot
            movieActorsInfo.text = item.actors

            item.images?.let {
                adapterActorList = ActorsListAdapter(it)
                recyclerViewActors.adapter = adapterActorList
            }

            item.genres?.let {
                adapterCategory = CategoryEachFilmListAdapter(it)
                recyclerViewCategory.adapter = adapterCategory
            }
        }, { volleyError ->
            showLoading(false)
            Toast.makeText(this, "Failed to load movie details. Please try again.", Toast.LENGTH_SHORT).show()
        })

        mRequestQueue.add(mStringRequest)
    }

    private fun initView() {
        titleTxt = findViewById(R.id.movieName)
        progressBar = findViewById(R.id.progressBarDetails)
        scrollViewDetails = findViewById(R.id.scrollViewDetail)
        pic2 = findViewById(R.id.picDetail)
        movieRateTxt = findViewById(R.id.movieStar)
        movieTimeTxt = findViewById(R.id.movieTime)
        movieSummaryInfo = findViewById(R.id.movieSummery)
        movieActorsInfo = findViewById(R.id.movieActores)
        backImg = findViewById(R.id.imgBack)
        recyclerViewCategory = findViewById(R.id.genreView)
        recyclerViewActors = findViewById(R.id.imageRecycle)
        recyclerViewActors.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        backImg.setOnClickListener { finish() }
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        scrollViewDetails.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
}