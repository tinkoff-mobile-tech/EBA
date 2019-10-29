package ru.tinkoff.sample_eba.search.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.subjects.Subject
import ru.tinkoff.sample_eba.R
import ru.tinkoff.sample_eba.extensions.inflate
import ru.tinkoff.sample_eba.search.SearchResultDiff
import ru.tinkoff.sample_eba.search.ViewTyped

class Adapter(
    private var itemsList: List<ViewTyped>,
    private val clickListener: Subject<ViewTyped>
) : RecyclerView.Adapter<BaseViewHolder<ViewTyped>>() {

    fun setList(newList: List<ViewTyped>) {
        val callback = SearchResultDiff(itemsList, newList)
        DiffUtil.calculateDiff(callback).dispatchUpdatesTo(this@Adapter)
        itemsList = newList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ViewTyped> {
        return when (viewType) {
            R.layout.item_some_ui -> SearchResultViewHolder(
                parent.inflate(R.layout.item_some_ui),
                clickListener
            )
            R.layout.item_loading -> BaseViewHolder<ViewTyped>(parent.inflate(R.layout.item_loading))
            else -> BaseViewHolder<ViewTyped>(parent.inflate(R.layout.item_empty_state))
        } as BaseViewHolder<ViewTyped>
    }


    override fun getItemViewType(position: Int): Int {
        return itemsList[position].viewType
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewTyped>, position: Int) {
        holder.bind(itemsList[position])
    }

    override fun getItemCount(): Int = itemsList.size
}