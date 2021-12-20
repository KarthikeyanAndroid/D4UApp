package com.d4u.shopping.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Karthikeyan V on 25,October,2021
 */
@Entity(tableName = "BookMark")
data class BookMarkModel(

        @ColumnInfo(name = "offer_name") var offerName: String,
        @ColumnInfo(name = "offer_image") var offerImage: String,
        @ColumnInfo(name = "business_name") var businessName: String,
        @ColumnInfo(name = "business_logo") var businessLogo: String,
        @ColumnInfo(name = "total_pages") var totalPages: String,
        @ColumnInfo(name = "created_at") var createdAt: String,
        @ColumnInfo(name = "total_viewer_cnt") var totalViewerCount: String,
        @ColumnInfo(name="customer_id") var customerId:String,

        ) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}