package com.example.binarysearch

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.time.Duration
import java.time.Instant
import kotlin.math.round
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lateinit var array:Array<Int>

        val arrayNum  = findViewById<EditText>(R.id.arrayNum)
        val searchTxt = findViewById<EditText>(R.id.searchTxt)

        val searchBtn = findViewById<Button>(R.id.searchBtn)
        val fillBtn   = findViewById<Button>(R.id.fillBtn)

        val resultTxt = findViewById<TextView>(R.id.resultTv)
        val timeTv    = findViewById<TextView>(R.id.timeTv)

        val listView  = findViewById<ListView>(R.id.arrayLv)


        fillBtn.setOnClickListener(){
            val num = arrayNum.text.toString().toInt()
            array = Array(num){0}
            for(i in 0 until num){
                array[i] = Random.nextInt()
            }
            array.sort()
            var adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
            listView.adapter = adapter
        }

        searchBtn.setOnClickListener(){
            val target = searchTxt.text.toString().toInt()

            var start = Instant.now()
            var result = binarySearch(array,target)
            var end = Instant.now()
            var duration = Duration.between(start, end)

            if(result == -1){
                resultTxt.text = "Sorry! Not Found!!!"
            }else{
                resultTxt.text = "The number ($target) has been found at index $result."
            }
            timeTv.text = "The SEARCHING execution time was ${duration.toMillis()} milliseconds."
        }
    }

    fun binarySearch(array: Array<Int>, target: Int): Int {
        var low = 0
        var high = array.size - 1

        while (low <= high) {
            val mid = (low + high) / 2

            if (array[mid] == target) {
                return mid
            } else if (array[mid] < target) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }

        return -1
    }
}