package com.tamimattafi.zennex.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val UI_DATE_PATTERN: String = "EEE, dd MMMM yyyy, HH:mm"

    fun toString(date: Date?, pattern: String): String {
        return if (date != null) {
            SimpleDateFormat(pattern, Locale.getDefault()).format(date)
        } else "～"
    }

    fun toString(unix: Long, pattern: String): String = toString(fromUnix(unix), pattern)

    fun toString(string: String?, firstPattern: String, secondPattern: String) =
        toString(fromString(string, firstPattern), secondPattern)

    fun fromString(string: String?, pattern: String): Date? {
        return if (string != null) {
            SimpleDateFormat(pattern, Locale.getDefault()).parse(string)
        } else null
    }

    fun fromUnix(unix: Long): Date? {
        return if (unix > 1) {
            Date(unix * 1000)
        } else null
    }

    fun toUnix(date: Date?): Long? {
        return if (date != null) {
            (date.time / 1000)
        } else null
    }

    fun getCurrentUnix(): Long {
        return toUnix(Calendar.getInstance().time)!!
    }

}