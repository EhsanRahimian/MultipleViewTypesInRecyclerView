package com.nicootech.multipleviewtypes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.nicootech.multipleviewtypes.R
import com.nicootech.multipleviewtypes.ui.adapter.Adapter

class MainActivity : AppCompatActivity() {

    private  lateinit var swiper : SwipeRefreshLayout
    private lateinit var recyclerView : RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findView()
        initList()
        reload()

    }

    private fun findView(){
        swiper = findViewById(R.id.swiper)
        recyclerView = findViewById(R.id.recyclerView)
    }
    private fun  initList(){
        layoutManager = LinearLayoutManager(this)
        adapter = Adapter()

        adapter.onLoadMore = {loadMore()}

        swiper.setOnRefreshListener {
            reload()
            swiper.isRefreshing = false
        }

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
    private fun reload(){
        recyclerView.post {
            adapter.reload(createDummyData(0,15))
        }
    }
    private fun loadMore(){
        recyclerView.post {
            adapter.reload(createDummyData(adapter.itemCount,10))
        }
    }

    private fun createDummyData(offset: Int, limit: Int): MutableList<String> {
        val list = mutableListOf<String>()

        for(i in offset..(offset + limit)){
            when((0..2).random()){
                0-> {
                    list.add("Type A")
                }
                1-> {
                    list.add("Type B")
                }
                2-> {
                    list.add("Type C")
                }
            }
        }
        return list
    }
}