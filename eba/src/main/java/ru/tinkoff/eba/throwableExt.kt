package ru.tinkoff.eba

import android.util.Log
import ru.tinkoff.eba.BuildConfig

fun Throwable.log(tag: String) {
    if (BuildConfig.DEBUG) {
        Log.e(tag, message, this)
    }
}