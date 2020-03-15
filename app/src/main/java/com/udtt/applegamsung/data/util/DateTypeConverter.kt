package com.udtt.applegamsung.data.util

import androidx.room.TypeConverter
import java.util.*

/**
 * Created By Yun Hyeok
 * on 3월 15, 2020
 */

class DateTypeConverter {

    @TypeConverter
    fun toDate(milliseconds: Long?): Date? = milliseconds?.let { Date(it) }

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time

}