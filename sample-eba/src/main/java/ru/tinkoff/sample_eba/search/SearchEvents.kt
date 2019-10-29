package ru.tinkoff.sample_eba.search

import io.reactivex.Observable
import ru.tinkoff.eba.BaseEvents
import ru.tinkoff.eba.actioncreators.EventObservable
import java.util.concurrent.TimeUnit

internal interface SearchEvents : BaseEvents {
    val loadEvent: Observable<Int>
    val searchQueryChangesEvent: Observable<String>
    val clearSearchClicked: EventObservable
    val searchResultClicked: Observable<SearchResultUi>
}

internal class SearchEventsImpl(
    override val unbindEvent: EventObservable,
    loadEvent: EventObservable,
    itemClicked: Observable<ViewTyped>,
    searchChanges: Observable<CharSequence>,
    override val clearSearchClicked: EventObservable
) : SearchEvents {

    override val searchResultClicked: Observable<SearchResultUi>
            = itemClicked.ofType(SearchResultUi::class.java)
    override val loadEvent: Observable<Int> = loadEvent.map { 500 }
    override val searchQueryChangesEvent: Observable<String> =
        searchChanges.map(CharSequence::toString).skip(1)
            .distinctUntilChanged()
            .debounce(200, TimeUnit.MILLISECONDS)
}