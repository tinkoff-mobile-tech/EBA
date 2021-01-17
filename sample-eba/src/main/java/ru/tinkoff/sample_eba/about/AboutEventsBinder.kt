package ru.tinkoff.sample_eba.about

import io.reactivex.Observable
import ru.tinkoff.eba.BaseEventsBinder
import ru.tinkoff.eba.actioncreators.Action
import ru.tinkoff.eba.actioncreators.ActionCreator
import ru.tinkoff.eba.actioncreators.ActionOnEvent

class AboutEventsBinder(private val setVersionName: ActionCreator<Observable<*>>,
                        private val openPolicyPrivacy: ActionOnEvent,
                        private val openProcessingPersonalData: ActionOnEvent
) :
        BaseEventsBinder<AboutEvents>() {

    override fun bindInternal(events: AboutEvents): Observable<Action> {
        return Observable.merge(setVersionName(events.bindEvent),
                                openPolicyPrivacy(events.openPolicyPrivacyEvent),
                                openProcessingPersonalData(events.openProcessingPersonalDataEvent))
    }
}