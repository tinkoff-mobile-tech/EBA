package ru.tinkoff.eba

interface TrackAnalytics<EVENTS : BaseEvents> {
    operator fun invoke(events: EVENTS): EVENTS
}

class EmptyAnalyticsTracker<EVENTS : BaseEvents> :
    TrackAnalytics<EVENTS> {
    override fun invoke(events: EVENTS): EVENTS = events
}