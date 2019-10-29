package ru.tinkoff.sample_eba.search.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

open class BaseViewHolder<in T>(override val containerView: View) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {

    open fun bind(item: T) = Unit
}