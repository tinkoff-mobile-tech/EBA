package ru.tinkoff.eba.actioncreators

import io.reactivex.Observable

typealias Action = () -> Unit
typealias ActionMapper<T> = (T) -> Action
typealias EventObservable = Observable<*>
typealias ActionOnEvent = ActionCreator<EventObservable>

interface ActionCreator<T> : (T) -> (Observable<Action>)

val EmptyAction: Action = {}

fun <T> ((T) -> Unit).asActionMapper(): ActionMapper<T> {
    return { parameter -> { invoke(parameter) } }
}

operator fun <T> ActionMapper<T>.plus(mapper: (T) -> Unit): ActionMapper<T> =
    { this(it) + mapper.asActionMapper().invoke(it) }

inline operator fun Action.plus(crossinline action: Action): Action {
    return {
        this()
        action()
    }
}