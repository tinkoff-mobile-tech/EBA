package ru.tinkoff.sample_eba.search

import io.reactivex.Observable
import ru.tinkoff.eba.BaseEventsBinder
import ru.tinkoff.eba.actioncreators.Action
import ru.tinkoff.eba.actioncreators.ActionCreator
import ru.tinkoff.eba.actioncreators.ActionOnEvent

internal class SearchEventsBinder(private val loadSomeData: ActionCreator<Observable<Int>>,
                                  private val searchSomeData: ActionCreator<Observable<String>>,
                                  private val showSearchItemDetails: ActionCreator<Observable<SearchResultUi>>,
                                  private val clearSearch: ActionOnEvent
) : BaseEventsBinder<SearchEvents>() {

    override fun bindInternal(events: SearchEvents): Observable<Action>
            = Observable.merge(loadSomeData(events.loadEvent),
                               searchSomeData(events.searchQueryChangesEvent),
                               showSearchItemDetails(events.searchResultClicked),
                               clearSearch(events.clearSearchClicked))
}