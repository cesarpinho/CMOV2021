package org.feup.cp.acme.room

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}