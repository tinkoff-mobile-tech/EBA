package ru.tinkoff.sample_eba.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun <T> View.inflate(
    @LayoutRes
    layout: Int, root: ViewGroup? = null, attachToRoot: Boolean = false
): T {
    @Suppress("UNCHECKED_CAST")
    return LayoutInflater.from(context)
        .inflate(layout, root ?: this as? ViewGroup, attachToRoot) as T
}