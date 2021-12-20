package com.d4u.shopping.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.d4u.shopping.data.BookMarkModel
import com.d4u.shopping.db.D4UDatabase
import com.d4u.shopping.db.D4UdaoAccess
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.reflect.Constructor
import javax.inject.Inject


/**
 * Created by Karthikeyan V on 25,October,2021
 */
class BookMarkRepository @Inject constructor(var d4UDatabase: D4UDatabase) {
//    private var d4UdaoAccess: D4UdaoAccess = d4UDatabase.d4uDdaoAccess()

    fun getBookMarks(): LiveData<List<BookMarkModel>> {
        return d4UDatabase.d4uDdaoAccess().getBookMark()
    }

    fun insertBookMark(bookMarkModel: BookMarkModel) {
        CoroutineScope(Dispatchers.IO).launch {
            d4UDatabase.d4uDdaoAccess().InsertData(bookMarkModel)
        }
    }


}