package ru.tinkoff.sample_eba.search

import ru.tinkoff.sample_eba.R
import androidx.recyclerview.widget.DiffUtil

data class SearchResultUi(override val uid: String,
                          val text: String,
                          override val viewType: Int = R.layout.item_some_ui) : ViewTyped

data class SearchResult(val text: String)

class ProgressItem(
    override val uid: String = "PROGRESS_ITEM",
    override val viewType: Int = R.layout.item_loading) : ViewTyped

class EmptyItem(
    override val uid: String = "EMPTY_ITEM",
    override val viewType: Int = R.layout.item_empty_state) : ViewTyped

interface ViewTyped {
    val uid: String
    val viewType: Int
}

class SearchResultDiff(private val oldList: List<ViewTyped>,
                       private val newList: List<ViewTyped>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)
            = oldList[oldItemPosition].uid == newList[newItemPosition].uid

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int)
            = (oldList[oldItemPosition] as? SearchResultUi)?.text == (newList[newItemPosition] as? SearchResultUi)?.text
}