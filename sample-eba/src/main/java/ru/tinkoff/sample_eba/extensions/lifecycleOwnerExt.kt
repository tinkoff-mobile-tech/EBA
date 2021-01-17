package ru.tinkoff.sample_eba.extensions

import androidx.lifecycle.Lifecycle.Event
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.disposables.Disposable
import java.util.concurrent.atomic.AtomicBoolean

fun LifecycleOwner.observe(event: Event): Observable<Event> {
    return Observable.create(LifecycleOwnerOnSubscribe(this, event))
}

internal class LifecycleOwnerOnSubscribe(
    private val owner: LifecycleOwner,
    private val event: Event
) : ObservableOnSubscribe<Event> {

    override fun subscribe(emitter: ObservableEmitter<Event>) {
        val listener = EmitterListener(emitter, event)
        emitter.setDisposable(listener)
        owner.lifecycle.addObserver(listener)
    }

    internal inner class EmitterListener(
        private val emitter: ObservableEmitter<Event>,
        private val event: Event
    ) : AtomicBoolean(), LifecycleEventObserver, Disposable {

        override fun onStateChanged(source: LifecycleOwner, event: Event) {
            if (owner == source && this.event == event) {
                emitter.onNext(event)
            }
        }

        override fun dispose() {
            if (compareAndSet(false, true)) {
                owner.lifecycle.removeObserver(this)
            }
        }

        override fun isDisposed(): Boolean {
            return get()
        }
    }
}