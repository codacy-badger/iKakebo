package eu.mmassi.ikakebo.data.local.room

import androidx.room.TypeConverter
import org.threeten.bp.ZonedDateTime

class ZonedDateTimeConverter {

    @TypeConverter
    fun toString(value: ZonedDateTime): String = value.toString()

    @TypeConverter
    fun fromString(value: String): ZonedDateTime = ZonedDateTime.parse(value)
}
