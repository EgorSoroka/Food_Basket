package com.george.food_basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.george.food_basket.db.DbManager

class UpdateItem : AppCompatActivity() {
    val dbManager = DbManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_item)
    }

    fun Update(view: View) {

    }
}