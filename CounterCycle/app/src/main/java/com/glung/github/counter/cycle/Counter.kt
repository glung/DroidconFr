package com.glung.github.counter.cycle

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import org.jetbrains.anko.button
import org.jetbrains.anko.sp
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import rx.Observable

val UP = 0
val DOWN = 1

fun main(context: Context, select: (Int) -> ActivityIO): Observable<View> =
        Observable
                // INTENT
                .merge(
                        select(UP).clickEvents().map { it -> +1 },
                        select(DOWN).clickEvents().map { it -> -1 }
                )
                // MODEL
                .startWith(0)
                .scan({ x, y -> x + y })
                // VIEW
                .map({ x -> view(context, x) })

fun view(context: Context, counter: Int): LinearLayout {
    with(context) {
        return verticalLayout {
            button(getString(R.string.up)) {
                id = UP
            }
            button(getString(R.string.down)) {
                id = DOWN
            }
            textView(counter.toString()) {
                textSize = sp(24).toFloat()
                gravity = Gravity.CENTER
            }
        }
    }
}