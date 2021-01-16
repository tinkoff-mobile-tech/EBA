package ru.tinkoff.eba.actioncreators

import io.reactivex.Observable

fun Observable<Action>.onErrorReturnAction(action: ActionMapper<String?>): Observable<Action> {
    return onErrorReturn { throwable -> action(throwable.message) }
}

fun Observable<Action>.onErrorReturnAction(action: Action): Observable<Action> {
    return onErrorReturn { _ -> action }
}