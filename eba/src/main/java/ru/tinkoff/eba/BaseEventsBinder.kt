package ru.tinkoff.eba

import android.annotation.SuppressLint
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import ru.tinkoff.eba.actioncreators.Action

abstract class BaseEventsBinder<in EVENTS : BaseEvents>(
    private val trackAnalytics: TrackAnalytics<EVENTS> = EmptyAnalyticsTracker(),
    private val uiScheduler: Scheduler = AndroidSchedulers.mainThread()
) {

    /**
     * Here is all magic:
     *  bindInternal - main method which:
     *  accept inheritor of BaseEvents interface and return (as always) Observable.merge() of all ActionCreator
     */
    @SuppressLint("CheckResult")
    fun bind(events: EVENTS) {
        bindInternal(trackAnalytics(events)).observeOn(uiScheduler)
            .takeUntil(events.unbindEvent)
            .subscribe(Action::invoke)
    }

    protected abstract fun bindInternal(events: EVENTS): Observable<Action>
}