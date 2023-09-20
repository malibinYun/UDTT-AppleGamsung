package com.udtt.applegamsung.util

fun <T> Result<List<T>>.getOrEmpty(): List<T> {
    return this.getOrNull().orEmpty()
}
