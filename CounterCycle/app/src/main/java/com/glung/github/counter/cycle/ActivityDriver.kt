package com.glung.github.counter.cycle

import android.app.Activity
import android.view.View
import com.jakewharton.rxbinding.view.RxView
import rx.Observable

data class ActivityIO(val observable: Observable<View>, val clickEvents: () -> Observable<Void>)

public fun makeActivityDriver(container: Activity): (view: Observable<View>) -> (Int) -> ActivityIO =
        {
            views ->
            makeElementSelector(renderRootElement(views, container))
        }

fun renderRootElement(views: Observable<View>, activity: Activity): Observable<View> =
        views.doOnNext { view -> activity.setContentView(view) }

fun makeElementSelector(view: Observable<View>): (Int) -> ActivityIO =
        {
            id ->
            ActivityIO(select(id, view), makeClickEventsSelector(select(id, view)))
        }

private fun select(id: Int, views: Observable<View>) =
        views.map { view -> view.findViewById(id) }

fun makeClickEventsSelector(view: Observable<View>): () -> Observable<Void> =
        { view.flatMap { RxView.clicks(it) }.share() }
