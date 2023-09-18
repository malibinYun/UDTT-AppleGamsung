package com.udtt.applegamsung.util

fun <T> Result<Collection<T>>.getOrEmpty(): Collection<T> {
    return this.getOrNull().orEmpty()
}
