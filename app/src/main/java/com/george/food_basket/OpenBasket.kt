package com.george.food_basket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.george.food_basket.db.DbManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_open_basket.*

class OpenBasket : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_basket)
    }
}
