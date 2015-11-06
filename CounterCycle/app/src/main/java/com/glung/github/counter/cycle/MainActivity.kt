package com.glung.github.counter.cycle

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.LinearLayout
import org.jetbrains.anko.button
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import rx.Observable

class MainActivity : AppCompatActivity() {
    val UP = 0
    val DOWN = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runCycle(
                { element -> main(element) },
                makeActivityDriver(this)
        )
    }
    
    fun main(select: (Int) -> ActivityIO): Observable<View> =
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
                    .map({ x -> view(this, x) })


    fun view(context: Context, counter: Int): LinearLayout {
        with(context) {
            return verticalLayout {
                button(getString(R.string.up)) {
                    id = UP
                }
                button(getString(R.string.down)) {
                    id = DOWN
                }
                textView(counter.toString())
            }
        }
    }

}
