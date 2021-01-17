package ru.tinkoff.sample_eba.search.list

import android.view.View
import android.widget.TextView
import io.reactivex.subjects.Subject
import ru.tinkoff.sample_eba.R
import ru.tinkoff.sample_eba.search.SearchResultUi
import ru.tinkoff.sample_eba.search.ViewTyped

class SearchResultViewHolder(itemView: View,
                             private val clicks: Subject<ViewTyped>) : BaseViewHolder<SearchResultUi>(itemView) {

    private val title: TextView = itemView.findViewById(R.id.title)

    override fun bind(item: SearchResultUi) {
        title.text = item.text
        itemView.setOnClickListener { clicks.onNext(item) }
    }
}