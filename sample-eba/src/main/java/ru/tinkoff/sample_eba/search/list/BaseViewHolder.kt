package ru.tinkoff.sample_eba.search.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<in T>(containerView: View) : RecyclerView.ViewHolder(containerView) {

    open fun bind(item: T) = Unit
}