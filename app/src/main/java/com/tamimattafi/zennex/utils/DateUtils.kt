package com.tamimattafi.zennex.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val UI_DATE_PATTERN: String = "EEE, dd MMMM yyyy, HH:mm"
    const val TIME_DATE_PATTERN: String = "HH:mm"
    const val NO_TIME_DATE_PATTERN: String = "EEEE, dd MMMM yyyy"
    const val NO_YEAR_DATE_PATTERN: String = "EEEE, dd MMMM HH:mm"
    const val NO_MONTH_DATE_PATTERN: String = "EEEE, HH:mm"
    const val MONTH_DATE_PATTERN: String = "dd MMMM, yyyy"

    fun toString(date: Date?, pattern: String): String {
        return if (date != null) {
            SimpleDateFormat(pattern, Locale.getDefault()).format(date)
        } else "～"
    }

    fun toString(unix: Long, pattern: String): String = toString(fromUnix(unix), pattern)

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

    fun getCalendar(date: Date? = null): Calendar {
        return Calendar.getInstance().apply {
            if (date != null) time = date
        }
    }

    fun getCurrentCalendar(): Calendar {
        return Calendar.getInstance()
    }

    fun getCurrentDate(): Date {
        return getCurrentCalendar().time
    }

    fun getCurrentUnix(): Long {
        return toUnix(Calendar.getInstance().time)!!
    }

    fun hasDatePassed(otherDate: Date?): Boolean {
        return if (otherDate != null) {
            Calendar.getInstance().time.time > otherDate.time
        } else true
    }

    fun getDateField(date: Date?, field: Int): Int {
        return getCalendar(date).get(field)
    }
}