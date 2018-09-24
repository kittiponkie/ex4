package com.egco428.ex04_listactivity01

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.course_item.view.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    val bundle = intent.extras
    var courseTitle: String = ""
    var courseDetail: String = ""
        var credit : String =""
        var imgpos : String = ""
        if (bundle != null){
            courseTitle = bundle.getString("cTitle")
            courseDetail = bundle.getString("cDetail")
            credit = bundle.getString("Credits")
            imgpos = bundle.getString("Pic")

            titleText.text = courseTitle
            descriptionText.text = courseDetail
            credits.text = credit

            val res = resources.getIdentifier("image1010"+imgpos, "drawable", packageName)
            imgCourse.imgCourse.setImageResource(res)
            /*val courseTitle = intent.getStringExtra("courseTitle")
            titleText.text = courseTitle

            val courseDesc = intent.getStringExtra("courseDesc")
            descriptionText.text = courseDesc*/
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        val id = item!!.getItemId()
        if(id == android.R.id.home){
            finish()
        }

        return super.onOptionsItemSelected(item)
    }
}
