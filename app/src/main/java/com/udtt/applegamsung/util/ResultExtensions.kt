package com.udtt.applegamsung.util

fun <T> Result<List<T>>.getOrEmpty(): List<T> {
    return this.getOrNull().orEmpty()
}

inline fun <T, R> Result<List<T>>.mapList(transform: (T) -> R): Result<List<R>> {
    return this.map { it.map(transform) }
}
