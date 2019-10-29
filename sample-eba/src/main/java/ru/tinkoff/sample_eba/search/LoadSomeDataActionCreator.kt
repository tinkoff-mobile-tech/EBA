package ru.tinkoff.sample_eba.search

import io.reactivex.Observable
import ru.tinkoff.eba.actioncreators.Action
import ru.tinkoff.eba.actioncreators.ActionCreator
import ru.tinkoff.eba.actioncreators.ActionMapper
import java.util.concurrent.TimeUnit

class LoadSomeDataActionCreator(
    private val someDataGenerator: (Int) -> Observable<List<SearchResult>>,
    private val showGeneratedData: ActionMapper<List<ViewTyped>>,
    private val someDataToSearchResultUi: (SearchResult) -> ViewTyped,
    private val showErrorAction: Action,
    private val showLoadingAction: Action
) : ActionCreator<Observable<Int>> {

    override fun invoke(event: Observable<Int>): Observable<Action> = event.flatMap {
        someDataGenerator(it)
            .delay(2, TimeUnit.SECONDS)
            .map { someDataList ->
                someDataList.map { someData -> someDataToSearchResultUi(someData) }
            }.map(showGeneratedData).onErrorReturnItem(showErrorAction)
            .startWith(showLoadingAction)
    }
}