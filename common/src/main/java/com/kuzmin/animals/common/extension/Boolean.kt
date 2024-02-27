package com.kuzmin.animals.common.extension

fun Boolean.toInt(): Int {
    return if (this) 1 else 0
}