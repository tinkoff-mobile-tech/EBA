package ru.tinkoff.sample_eba.search.di

import ru.tinkoff.eba.actioncreators.Action
import ru.tinkoff.eba.actioncreators.ActionMapper
import ru.tinkoff.eba.actioncreators.toActionCreator
import ru.tinkoff.sample_eba.search.*
import ru.tinkoff.sample_eba.search.SearchEventsBinder

internal class SearchComponent(
    private val showList: ActionMapper<List<ViewTyped>>,
    private val showClickedItemAction: ActionMapper<SearchResultUi>,
    private val showEmptyAction: Action,
    private val showErrorAction: Action,
    private val clearSearchAction: Action,
    private val showLoadingAction: Action
) {
    private val someDataList: MutableList<SearchResult> = mutableListOf()

    fun binder(): SearchEventsBinder {
        val loadSomeData = LoadSomeDataActionCreator(SomeDataGenerator(someDataList),
                                                     showList,
                                                     SomeDataToSomeUi,
                                                     showErrorAction,
                                                     showLoadingAction)
        val searchSomeData = SearchSomeDataActionCreator({ someDataList },
                                                         SomeDataToSomeUi,
                                                         showList,
                                                         showEmptyAction)
        return SearchEventsBinder(loadSomeData,
                                  searchSomeData,
                                  showClickedItemAction.toActionCreator(),
                                  clearSearchAction.toActionCreator())
    }
}