package com.george.food_basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.george.food_basket.db.DbManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_open_basket.*

class OpenBasket : AppCompatActivity() {

    val dbManager = DbManager(this)
    val adapterBasket = BasketAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_basket)
        init()
    }

    fun init(){
        recyclerProd.layoutManager = LinearLayoutManager(this)
        recyclerProd.adapter = adapterBasket
    }

    fun fillAdapter(){
        adapterBasket.updateAdapter(dbManager.readDbData())
    }


    override fun onResume() {
        super.onResume()
        dbManager.openDB()
        fillAdapter()
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }
}
