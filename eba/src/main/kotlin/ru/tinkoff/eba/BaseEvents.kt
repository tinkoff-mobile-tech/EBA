package ru.tinkoff.eba

import ru.tinkoff.eba.actioncreators.EventObservable

interface BaseEvents {
    val unbindEvent: EventObservable
}