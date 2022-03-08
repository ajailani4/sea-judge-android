package com.example.seajudge.util

import java.text.SimpleDateFormat
import java.util.*

class Formatter {
    companion object {
        fun formatDate(strDate: String): String {
            val localeID = Locale("in", "ID")
            val date = SimpleDateFormat("yyyy-MM-dd", localeID).parse(strDate)

            return SimpleDateFormat("dd MMM yyyy", localeID).format(date!!)
        }
    }
}