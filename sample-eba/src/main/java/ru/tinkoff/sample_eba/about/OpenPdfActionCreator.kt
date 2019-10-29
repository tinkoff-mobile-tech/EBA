package ru.tinkoff.sample_eba.about

import io.reactivex.Observable
import ru.tinkoff.eba.actioncreators.Action
import ru.tinkoff.eba.actioncreators.ActionMapper
import ru.tinkoff.eba.actioncreators.ActionOnEvent
import ru.tinkoff.eba.actioncreators.EventObservable

private const val FILE_NAME_DELIMITER = "/"

class OpenPdfActionCreator(
    private val openPdfAction: ActionMapper<Pair<String, String>>,
    private val pdfUrl: String
) :
    ActionOnEvent {

    override fun invoke(event: EventObservable): Observable<Action> {
        return event.map {
            openPdfAction(pdfUrl to pdfUrl.substringAfterLast(FILE_NAME_DELIMITER))
        }
    }
}