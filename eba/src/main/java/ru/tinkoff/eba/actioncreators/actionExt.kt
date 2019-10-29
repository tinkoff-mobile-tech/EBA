package ru.tinkoff.eba.actioncreators

import io.reactivex.Observable
import ru.tinkoff.eba.log

fun Observable<Action>.onErrorReturnAction(action: ActionMapper<String?>): Observable<Action> {
    return onErrorReturn { throwable ->
        throwable.run {
            log("OnErrorActionReturn")
            action(throwable.message)
        }
    }
}

fun Observable<Action>.onErrorReturnAction(action: Action): Observable<Action> {
    return onErrorReturn { throwable ->
        throwable.log("OnErrorActionReturn")
        action
    }
}