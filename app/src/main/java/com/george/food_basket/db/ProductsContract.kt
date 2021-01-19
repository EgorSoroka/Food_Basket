package com.george.fridge.db

import android.provider.BaseColumns

object ProductsContract : BaseColumns {

        const val TABLE_NAME = "Food"
        const val COLUMN_NAME_TITLE = "Product"
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Products.db"

        const val SQL_CREATE_ENTRIES =
                "CREATE TABLE IF NOT EXISTS ${ProductsContract.TABLE_NAME} (" +
                             "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                             "${ProductsContract.COLUMN_NAME_TITLE} TEXT,"

        const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ProductsContract.TABLE_NAME}"
    }
