package com.george.fridge.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager(context : Context) {
    val ProductsDBHelper = ProductsDBHelper(context)

    var db: SQLiteDatabase? = null

    fun openDB(){
        db = ProductsDBHelper.writableDatabase
    }

    fun insertToDB(product: String){
        val values = ContentValues().apply {
            put(ProductsContract.COLUMN_NAME_TITLE, product)
        }
        db?.insert(ProductsContract.TABLE_NAME, null, values)
    }

    fun readDbData(): ArrayList<String>{
        val dataList = ArrayList<String>()

        val cursor = db?.query(ProductsContract.TABLE_NAME,  null, null, null, null, null, null,)

        with(cursor){
            while (this?.moveToNext()!!){
                val dataText = cursor?.getString(cursor.getColumnIndex(ProductsContract.COLUMN_NAME_TITLE))
                dataList.add(dataText.toString())
            }
        }
        cursor?.close()
        return dataList
    }

    fun closeDb(){
        ProductsDBHelper.close()
    }
}