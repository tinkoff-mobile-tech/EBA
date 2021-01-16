package ru.tinkoff.sample_eba.about

import ru.tinkoff.eba.BaseEvents
import ru.tinkoff.eba.actioncreators.EventObservable

interface AboutEvents : BaseEvents {

    val bindEvent: EventObservable
    val openPolicyPrivacyEvent: EventObservable
    val openProcessingPersonalDataEvent: EventObservable
}

class AboutEventsImpl(override val bindEvent: EventObservable,
                      override val openPolicyPrivacyEvent: EventObservable,
                      override val openProcessingPersonalDataEvent: EventObservable,
                      override val unbindEvent: EventObservable
) : AboutEvents