package com.d4u.shopping.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.d4u.shopping.D4UApplication
import com.d4u.shopping.data.BookMarkModel


/**
 * Created by Karthikeyan V on 25,October,2021
 */

@Database(
        version = 2,
        entities = [BookMarkModel::class])
/*
@Database(entities = [BookMarkModel::class], version = 2,
        autoMigrations = [AutoMigration(from = 1, to = 2)])
*/
abstract class D4UDatabase : RoomDatabase() {
    abstract fun d4uDdaoAccess(): D4UdaoAccess

}