package com.george.food_basket.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ProductsDBHelper(context : Context) : SQLiteOpenHelper(context, ProductsContract.DATABASE_NAME, null, ProductsContract.DATABASE_VERSION ) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(ProductsContract.SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
       db?.execSQL(ProductsContract.SQL_DELETE_ENTRIES)
        onCreate(db)
    }


}