package com.udtt.applegamsung.data.database.cursor

import android.database.Cursor

fun Cursor.getStringColumnOf(columnName: String): String {
    val columnIndex = this.getColumnIndexOrThrow(columnName)
    return this.getString(columnIndex)
}

// 0 - false, 1 - true
fun Cursor.getBooleanColumnOf(columnName: String): Boolean {
    val columnIndex = this.getColumnIndexOrThrow(columnName)
    return this.getInt(columnIndex) == 1
}

fun Cursor.getIntColumnOf(columnName: String): Int {
    val columnIndex = this.getColumnIndexOrThrow(columnName)
    return this.getInt(columnIndex)
}

fun Cursor.getLongColumnOf(columnName: String): Long {
    val columnIndex = this.getColumnIndexOrThrow(columnName)
    return this.getLong(columnIndex)
}
