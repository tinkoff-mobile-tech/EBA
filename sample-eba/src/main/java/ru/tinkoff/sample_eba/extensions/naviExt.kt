package ru.tinkoff.sample_eba.extensions

import com.trello.navi2.Event
import com.trello.navi2.NaviComponent
import com.trello.navi2.rx.RxNavi
import io.reactivex.Observable

fun <T> NaviComponent.observe(event: Event<T>): Observable<T> = RxNavi.observe(this, event)