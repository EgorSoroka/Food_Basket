package com.george.food_basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.george.food_basket.db.DbManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_open_basket.*

class OpenBasket : AppCompatActivity() {

    val dbManager = DbManager(this)
    val adapterBasket = BasketAdapter(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_basket)
        init()
    }

    fun init(){
        recyclerProd.layoutManager = LinearLayoutManager(this)
        val swapHelper = getSwapD()
        swapHelper.attachToRecyclerView(recyclerProd)
        recyclerProd.adapter = adapterBasket
    }

    fun fillAdapter(){
        adapterBasket.updateAdapter(dbManager.readDbData())
    }

    private fun getSwapD() : ItemTouchHelper{
        return ItemTouchHelper(object:ItemTouchHelper.
        SimpleCallback(0, ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapterBasket.removeItem(viewHolder.adapterPosition, dbManager)
            }
        })
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
