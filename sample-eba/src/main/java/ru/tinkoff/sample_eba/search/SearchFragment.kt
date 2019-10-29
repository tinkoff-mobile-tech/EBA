package ru.tinkoff.sample_eba.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_search.*
import ru.tinkoff.eba.actioncreators.plus
import ru.tinkoff.sample_eba.R
import ru.tinkoff.sample_eba.base.BaseFragment
import ru.tinkoff.sample_eba.search.di.SearchComponent
import ru.tinkoff.sample_eba.search.list.Adapter

class SearchFragment : BaseFragment(R.layout.fragment_search) {

    @SuppressLint("ShowToast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val itemClicked = PublishSubject.create<ViewTyped>()
        val adapter = Adapter(emptyList(), itemClicked)
        recyclerView.adapter = adapter
        val errorToast = Toast.makeText(context, R.string.input_value_too_large, Toast.LENGTH_LONG)
        val scrollToTop = { recyclerView.scrollToPosition(0) }
        val component = SearchComponent(showList = { { adapter.setList(it) }  + scrollToTop },
                                        showErrorAction = errorToast::show,
                                        showClickedItemAction = { {
                                            Toast.makeText(context, "${it.text} clicked!", Toast.LENGTH_LONG).show()
                                        } },
                                        showEmptyAction = { adapter.setList(listOf(EmptyItem())) },
                                        clearSearchAction = { searchField.setText("") },
                                        showLoadingAction = { adapter.setList(listOf(ProgressItem())) })

        val events = SearchEventsImpl(unbindEvent = unBindEvent,
                                      loadEvent = bindEvent,
                                      itemClicked = itemClicked,
                                      searchChanges = searchField.textChanges(),
                                      clearSearchClicked = clearSearch.clicks())

        component.binder().bind(events)
    }
}