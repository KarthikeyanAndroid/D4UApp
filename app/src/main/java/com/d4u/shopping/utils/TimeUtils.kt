package com.oviesmarterware.ovie.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class TimeUtils {

    companion object {
        val simpleDateFormat= SimpleDateFormat("yyyy-MM-dd HH:MM:SS")
        val currentDT: String = simpleDateFormat.format(Date())

        fun getTimeZone(): String {
            val timeZone: TimeZone = TimeZone.getDefault()
            return timeZone.id
        }


        fun getCountOfDays(createdDateString: String?, expireDateString: String?, strFormat: String?): String? {
            val dateFormat = SimpleDateFormat( /*"dd/MM/yyyy hh:mm:ss"*/strFormat, Locale.getDefault())
            var createdConvertedDate: Date? = null
            var expireCovertedDate: Date? = null
            var todayWithZeroTime: Date? = null
            try {
                createdConvertedDate = dateFormat.parse(createdDateString)
                expireCovertedDate = dateFormat.parse(expireDateString)
                val today = Date()
                todayWithZeroTime = dateFormat.parse(dateFormat.format(today))
            } catch (e: ParseException) {
                e.printStackTrace()
            }
            val cYear: Int
            val cMonth: Int
            val cDay: Int
            if (createdConvertedDate!!.after(todayWithZeroTime)) {
                val cCal = Calendar.getInstance()
                cCal.time = createdConvertedDate
                cYear = cCal[Calendar.YEAR]
                cMonth = cCal[Calendar.MONTH]
                cDay = cCal[Calendar.DAY_OF_MONTH]
            } else {
                val cCal = Calendar.getInstance()
                cCal.time = todayWithZeroTime
                cYear = cCal[Calendar.YEAR]
                cMonth = cCal[Calendar.MONTH]
                cDay = cCal[Calendar.DAY_OF_MONTH]
            }


            /*Calendar todayCal = Calendar.getInstance();
    int todayYear = todayCal.get(Calendar.YEAR);
    int today = todayCal.get(Calendar.MONTH);
    int todayDay = todayCal.get(Calendar.DAY_OF_MONTH);
    */
            val eCal = Calendar.getInstance()
            eCal.time = expireCovertedDate
            val eYear = eCal[Calendar.YEAR]
            val eMonth = eCal[Calendar.MONTH]
            val eDay = eCal[Calendar.DAY_OF_MONTH]
            val date1 = Calendar.getInstance()
            val date2 = Calendar.getInstance()
            date1.clear()
            date1[cYear, cMonth] = cDay
            date2.clear()
            date2[eYear, eMonth] = eDay
            val diff = date2.timeInMillis - date1.timeInMillis
            val dayCount = diff.toFloat() / (24 * 60 * 60 * 1000)
            return if (dayCount <= 0) {
                "Expired"
            } else "" + dayCount.toInt() + " More Days"
        }
    }


}