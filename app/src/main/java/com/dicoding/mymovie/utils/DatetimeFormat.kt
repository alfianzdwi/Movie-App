package com.dicoding.mymovie.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

class DatetimeFormat {
    fun changeFormatDate(changeFormat: String): String {
        try {
            @SuppressLint("SimpleDateFormat") val formatter: DateFormat =
                SimpleDateFormat("yyyy-MM-dd")

            @SuppressLint("SimpleDateFormat") val dateFormat =
                SimpleDateFormat("MMM dd, yyyy")

            val date = formatter.parse(changeFormat)

            return dateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return changeFormat
    }
}