package com.glung.github.counter.redux

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.redux.Store
import com.redux.Subscriber

class MainActivity : AppCompatActivity(), Subscriber {
    val store: Store<Action, Model> = Store.create(Model(), reducer)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        findViewById(R.id.up).setOnClickListener { store.dispatch(Action.INCREMENT) }
        findViewById(R.id.down).setOnClickListener { store.dispatch(Action.DECREMENT) }

        store.subscribe(this)
        store.dispatch(Action.INIT)
    }

    override fun onStateChanged() {
        (findViewById(R.id.counter) as TextView).text = store.state.counter.toString()
    }
}
