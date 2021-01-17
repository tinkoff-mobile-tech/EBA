package ru.tinkoff.eba.actioncreators

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Observable.just

fun Action.toActionCreator(): ActionOnEvent {
    return ActionOnEvent { event -> event.map { this@toActionCreator } }
}

fun Action.toActionCreator(
    interactor: () -> Completable,
    onErrorAction: Action = EmptyAction
): ActionOnEvent {
    return ActionOnEvent { event ->
        event.flatMap {
            interactor().andJust(this@toActionCreator)
                .onErrorReturnAction(onErrorAction)
        }
    }
}

fun <T> Action.toTypedActionCreator(extraAction: T.() -> Unit): ActionCreator<Observable<T>> {
    return ActionCreator { event ->
        event.map {
            extraAction(it)
            this@toTypedActionCreator
        }
    }
}

fun <T> Action.toActionCreator(interactor: (T) -> Completable): ActionCreator<Observable<T>> {
    return ActionCreator { event -> event.flatMap { interactor(it).andJust(this@toActionCreator) } }
}

fun <T> Action.toStartWithActionCreator(
    interactor: (T) -> Completable
): ActionCreator<Observable<T>> {
    return ActionCreator { event ->
        event.flatMap { interactor(it).startWith(just(this@toStartWithActionCreator)) }
    }
}

fun <T> createEmptyTypedActionCreator(
    interactor: (T) -> Completable
): ActionCreator<Observable<T>> {
    return ActionCreator { event -> event.flatMap { interactor(it).andJust(EmptyAction) } }
}

fun createEmptyActionCreator(interactor: () -> Completable): ActionOnEvent {
    return ActionOnEvent { event -> event.flatMap { interactor().andJust(EmptyAction) } }
}

fun createEmptyActionCreator(
    interactor: () -> Completable,
    onErrorAction: Action = EmptyAction
): ActionOnEvent {
    return ActionOnEvent { event ->
        event.flatMap {
            interactor().andJust(EmptyAction)
                .onErrorReturnAction(onErrorAction)
        }
    }
}

fun createEmptyActionCreator(): ActionOnEvent {
    return ActionOnEvent { event -> event.map { EmptyAction } }
}

fun <T> ActionMapper<T>.toActionCreator(): ActionCreator<Observable<T>> {
    return ActionCreator { event -> event.map(this@toActionCreator) }
}

fun <R> ActionMapper<R>.toSimpleActionCreator(mapper: () -> R): ActionCreator<Observable<*>> {
    return ActionCreator { event -> event.map { this@toSimpleActionCreator(mapper()) } }
}

fun <T> createEmptyActionCreator(mapper: (T) -> Unit): ActionCreator<Observable<T>> {
    return ActionCreator { event ->
        event.map { value ->
            mapper(value)
            EmptyAction
        }
    }
}

fun createSimpleActionCreator(action: () -> Action): ActionOnEvent {
    return ActionOnEvent { event -> event.map { action() } }
}

fun <T, R> ActionMapper<R>.toActionCreator(
    interactor: (T) -> Observable<R>
): ActionCreator<Observable<T>> {
    return ActionCreator { event ->
        event.flatMap(interactor::invoke)
            .map(this@toActionCreator)
    }
}

fun <R> ActionMapper<R>.toActionCreator(interactor: () -> Observable<R>): ActionOnEvent {
    return ActionOnEvent { event -> event.switchMap { interactor().map(this@toActionCreator) } }
}

fun <T, R> createEmptyTypedActionCreator(
    interactor: (R) -> Completable,
    mapper: (T) -> R
): ActionCreator<Observable<T>> {
    return ActionCreator { event -> event.flatMap { interactor(mapper(it)).andJust(EmptyAction) } }
}

private fun <T> Completable.andJust(item: T): Observable<T> {
    return andThen(just(item))
}