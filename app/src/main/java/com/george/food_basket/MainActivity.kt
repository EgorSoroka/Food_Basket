package com.george.food_basket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.george.food_basket.db.DbManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val dbManager = DbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbManager.openDB()
    }

    fun onClickAdd(view: View) {
        dbManager.insertToDB(nameProd.text.toString())
    }

    fun openBasket(view: View) {
        val intent = Intent(this, OpenBasket::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()
    }

}