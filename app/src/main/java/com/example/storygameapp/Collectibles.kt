package com.example.storygameapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storygameapp.db.DBHelper
import com.example.storygameapp.model.CollectModel
import com.example.storygameapp.rv_adapter.CollectRV

class Collectibles : AppCompatActivity() {
    @SuppressLint("Range")
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collectible_screen)

        val listData = ArrayList<CollectModel>()
        val db = DBHelper(this, null)

        val rView = findViewById<RecyclerView>(R.id.collect_rv)
        rView.layoutManager = LinearLayoutManager(this)

        // populate RV
        val cursor = db.getCollectData()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                listData.add(
                    CollectModel(
                        cursor.getString(cursor.getColumnIndex("img_path")),
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("desc"))
                    )
                )
            }

            val adapter = CollectRV(listData)
            rView.adapter = adapter
        }
    }
}