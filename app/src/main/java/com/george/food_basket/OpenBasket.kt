package com.george.food_basket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.george.food_basket.db.DbManager
import kotlinx.android.synthetic.main.activity_open_basket.*

class OpenBasket : AppCompatActivity() {

    val dbManager = DbManager(this)
    val adapterBasket = BasketAdapter(ArrayList(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_basket)
        init()
    }

    fun init() {
        recyclerProd.layoutManager = LinearLayoutManager(this)
        val swapHelperD = getSwapD()
        swapHelperD.attachToRecyclerView(recyclerProd)
        val swapHelperU = getSwapU()
        swapHelperU.attachToRecyclerView(recyclerProd)
        recyclerProd.adapter = adapterBasket
    }

    fun fillAdapter() {
        adapterBasket.updateAdapter(dbManager.readDbData())
    }

    private fun getSwapD(): ItemTouchHelper {
        return ItemTouchHelper(object : ItemTouchHelper.
        SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                adapterBasket.removeItem(viewHolder.adapterPosition, dbManager)
                val text = "Deleted!"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        })
    }

    private fun getSwapU(): ItemTouchHelper {
        return ItemTouchHelper((object : ItemTouchHelper.
        SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val intent = Intent(this@OpenBasket, UpdateItem::class.java)
                startActivity(intent)
            }
        }))
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


