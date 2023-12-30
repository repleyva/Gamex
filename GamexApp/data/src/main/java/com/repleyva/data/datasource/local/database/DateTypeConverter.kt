package com.repleyva.data.datasource.local.database

import androidx.room.TypeConverter
import com.repleyva.common.utils.converters.ConverterDate
import com.repleyva.common.utils.extensions.toDate
import com.repleyva.common.utils.extensions.toString
import java.util.Date

class DateTypeConverter {

    @TypeConverter
    fun fromDateToString(date: Date?): String? {
        return date?.toString(ConverterDate.SQL_DATE)
    }

    @TypeConverter
    fun fromStringToDate(dateString: String?): Date? {
        return dateString?.toDate()
    }

}