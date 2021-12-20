package com.d4u.shopping.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.d4u.shopping.data.BookMarkModel
import com.d4u.shopping.repository.BookMarkRepository
import com.d4u.shopping.utils.CustomLiveData
import java.lang.reflect.Constructor
import javax.inject.Inject


/**
 * Created by Karthikeyan V on 25,October,2021
 */
class BookMarkViewModel @Inject constructor(var repository: BookMarkRepository) : ViewModel() {
    var bookMarkLiveData: CustomLiveData<ArrayList<BookMarkViewModel>>? = null

    fun getAllBookmark(): LiveData<List<BookMarkModel>> {
        return repository.getBookMarks()
    }

    fun addBookMark(bookMarkModel: BookMarkModel) {
        repository.insertBookMark(bookMarkModel)
    }


}