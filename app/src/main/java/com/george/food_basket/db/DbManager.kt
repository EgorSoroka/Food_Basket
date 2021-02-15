package com.george.food_basket.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import androidx.core.database.getIntOrNull

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

    fun deleteOfDB(id: String) {
        val selection = BaseColumns._ID + "=$id"
        db?.delete(ProductsContract.TABLE_NAME,selection, null)
    }

    fun readDbData(): ArrayList<ListItem> {
        val dataList = ArrayList<ListItem>()
        val cursor = db?.query(
            ProductsContract.TABLE_NAME, null, null, null, null, null, null)

        while (cursor?.moveToNext()!!) {
            val dataProd =
                cursor.getString(cursor.getColumnIndex(ProductsContract.COLUMN_NAME_TITLE))
            val dataQuantity =
                cursor.getInt(cursor.getColumnIndex(ProductsContract.COLUMN_NAME_QUANTITY))
            val dataId =
                cursor.getInt(cursor.getColumnIndex(BaseColumns._ID))
            val item = ListItem()
            item.product = dataProd
            item.quantity = dataQuantity
            item.id = dataId
            dataList.add(item)
        }
        cursor.close()
        return dataList
    }

    fun closeDb() {
        ProductsDBHelper.close()
    }
}