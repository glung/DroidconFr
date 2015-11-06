package com.glung.github.counter.cycle

import android.view.View
import rx.Observable
import rx.Subscription
import rx.subjects.ReplaySubject

public fun runCycle(main: ((Int) -> (ActivityIO)) -> Observable<View>,
                    driver: (Observable<View>) -> (Int) -> ActivityIO): Subscription {
    val views = ReplaySubject.create<View>(1)
    return main(driver(views))
            .doOnError { it.printStackTrace() }
            .subscribe(views)
}