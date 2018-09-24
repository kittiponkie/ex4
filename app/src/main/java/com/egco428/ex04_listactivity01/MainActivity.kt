package com.egco428.ex04_listactivity01

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.course_item.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = DataProvider.getData()
        val courseArrayAdapter = CourseArrayAdapter(this, 0, data!!)

        courseList.setAdapter(courseArrayAdapter)


        courseList.setOnItemClickListener { adapterView, view, position, id ->
            val course = data.get(position)
            var imgpos = position%3+1
            displayDetail(course,imgpos.toString())

        }
    }

    private fun displayDetail(course: Course,imgpos: String) {
        //Log.d("MainActivity", "Course: " + course.title)
        val intent = Intent(this,DetailActivity::class.java)
        intent.putExtra("cTitle", course.title)
        intent.putExtra("cDetail", course.description)
        intent.putExtra("Credits", course.credits.toString())
        intent.putExtra("Pic", imgpos)
        startActivity(intent)
    }

    private class CourseArrayAdapter(var context: Context, resource: Int, var objects: ArrayList<Course>): BaseAdapter(){
        override fun getCount(): Int {
            return objects.size
        }

        override fun getItem(position: Int): Any {
            return objects[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

            val course = objects[position]
            val rowMain:View
            if(convertView == null){
                val inflator = context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                rowMain  = inflator.inflate(R.layout.course_item, null)
                val viewHolder = ViewHolder(rowMain.titleText,rowMain.imgCourse)
                rowMain.tag = viewHolder
            }
            else{
                rowMain = convertView
            }

            val viewHolder = rowMain.tag as ViewHolder
            viewHolder.title.text = course.title
            var imgpos = position%3+1
            val res = context.resources.getIdentifier("image1010"+imgpos, "drawable", context.packageName)
            viewHolder.img.setImageResource(res)

            return rowMain
        }

        private class ViewHolder(val title:TextView,val img:ImageView){

        }
    }
}