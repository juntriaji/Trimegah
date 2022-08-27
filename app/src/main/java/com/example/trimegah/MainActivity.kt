package com.example.trimegah

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trimegah.databinding.ActivityMainBinding
import com.example.trimegah.model.RunningTradeModel
import com.example.trimegah.util.Common
import com.example.trimegah.util.MainAdapter
import java.util.*
import kotlin.concurrent.schedule

class MainActivity : AppCompatActivity() {
    //var binding : ActivityMainBinding? = null
    val handler = Handler(Looper.myLooper()!!)
    val maxRow: Int = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = MainAdapter(R.layout.cell, mutableListOf())
        binding.recyclerView.adapter = adapter
        for (i in 0..maxRow)
        {
            adapter.data.add(i, Common().generateRandom())
            adapter.notifyItemChanged(i)
        }
        //adapter = MainAdapter(R.layout.cell, data)
        print(adapter.data.size)
        //adapter.notifyDataSetChanged()

        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        //execTimer()
    }
    private fun execTimer(){
        var row: Int = 0
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val adapter = binding.recyclerView.adapter as MainAdapter

        Timer().scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                if(row==maxRow) row = 0
                println(row)
                println(Common().generateRandom().toString())
                handler.post {
                    //adapter.data[row] = Common.generateRandom()
                    adapter.setData(Common().generateRandom(), row)
                }



                row++

            }
        }, 0, 1000)

    }
}