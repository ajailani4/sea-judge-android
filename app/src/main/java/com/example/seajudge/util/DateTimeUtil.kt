package com.example.seajudge.util

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import java.util.*

fun Context.showDatePicker(onDateChanged: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val calYear = calendar.get(Calendar.YEAR)
    val calMonth = calendar.get(Calendar.MONDAY)
    val calDay = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(
        this,
        { _, year, month, dayOfMonth ->
            onDateChanged(String.format("%02d-%02d-%02d", year, month + 1, dayOfMonth))
        },
        calYear,
        calMonth,
        calDay
    ).show()
}

fun Context.showTimePicker(onTimeChanged: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val calHour = calendar.get(Calendar.HOUR_OF_DAY)
    val calMinute = calendar.get(Calendar.MINUTE)

    TimePickerDialog(
        this,
        { _, hour, minute ->
            onTimeChanged(String.format("%02d:%02d", hour, minute))
        },
        calHour,
        calMinute,
        true
    ).show()
}