package com.d4u.shopping.interfaces

import android.view.View
import com.d4u.shopping.data.HomeProductList

/**
 * Created by Karthikeyan on 06/03/2021.
 */
interface IItemClickListenrer {

    fun itemClick(pos: Int, view: View, catId: String, businessId: String,state:Int)
    fun bookMarkClick(homeProductList: HomeProductList, view: View) {

    }

}