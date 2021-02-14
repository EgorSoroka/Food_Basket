package com.george.food_basket.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager(context: Context) {
    private val ProductsDBHelper = ProductsDBHelper(context)

    var db: SQLiteDatabase? = null

    fun openDB() {
        db = ProductsDBHelper.writableDatabase
    }

    fun insertToDB(product: String, quantity: String) {
        val values = ContentValues().apply {
            put(ProductsContract.COLUMN_NAME_TITLE, product)
            put(ProductsContract.COLUMN_NAME_QUANTITY, quantity)
        }
        db?.insert(ProductsContract.TABLE_NAME, null, values)
    }

    fun readDbData(): ArrayList<ListItem> {
        val dataList = ArrayList<ListItem>()

        val cursor = db?.query(ProductsContract.TABLE_NAME, null, null, null, null, null, null)

        with(cursor) {
            while (this?.moveToNext()!!) {

                val dataProd =
                    cursor?.getString(cursor.getColumnIndex(ProductsContract.COLUMN_NAME_TITLE))
                val dataQuantity: Int? =
                    cursor?.getInt(cursor.getColumnIndex(ProductsContract.COLUMN_NAME_QUANTITY))
                val item = ListItem()
                item.product = dataProd.toString()
                item.quantity = dataQuantity.toString()

                dataList.add(item)
            }
        }
        cursor?.close()
        return dataList
    }

    fun closeDb() {
        ProductsDBHelper.close()
    }
}