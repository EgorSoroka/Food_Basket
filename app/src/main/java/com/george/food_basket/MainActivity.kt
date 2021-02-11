package com.george.food_basket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.george.food_basket.db.DbManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val dbManager = DbManager(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun onClickAdd(view: View) {

        if (nameProd.text.toString() != ""){
            dbManager.insertToDB(nameProd.text.toString())
            val text = nameProd.text.toString() + " added!!"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }

    }

    fun openBasket(view: View) {
        val intent = Intent(this, OpenBasket::class.java)
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        dbManager.closeDb()

    }

    override fun onResume() {
        super.onResume()
        dbManager.openDB()
    }

}