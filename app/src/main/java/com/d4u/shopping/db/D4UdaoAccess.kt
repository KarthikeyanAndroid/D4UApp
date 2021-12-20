package com.d4u.shopping.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.d4u.shopping.data.BookMarkModel


/**
 * Created by Karthikeyan V on 25,October,2021
 */
@Dao
interface D4UdaoAccess {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(bookMarkModel: BookMarkModel)

    @Query("SELECT * FROM BookMark")
    fun getBookMark(): LiveData<List<BookMarkModel>>



}