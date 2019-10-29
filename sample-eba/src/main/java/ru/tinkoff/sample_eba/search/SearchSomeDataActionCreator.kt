package ru.tinkoff.sample_eba.search

import io.reactivex.Observable
import ru.tinkoff.eba.actioncreators.Action
import ru.tinkoff.eba.actioncreators.ActionCreator
import ru.tinkoff.eba.actioncreators.ActionMapper

class SearchSomeDataActionCreator(
    private val GetSomeDataList: () -> List<SearchResult>,
    private val someDataToSearchResultUi: (SearchResult) -> SearchResultUi,
    private val showList: ActionMapper<List<SearchResultUi>>,
    private val showEmptyStateAction: Action
) : ActionCreator<Observable<String>> {
    override fun invoke(searchEvent: Observable<String>): Observable<Action> {
        return searchEvent.map { search -> GetSomeDataList().filter { it.text.contains(search) } }
            .map { list ->
                if (list.isEmpty()) showEmptyStateAction else showList(list.map(someDataToSearchResultUi))
            }
    }
}