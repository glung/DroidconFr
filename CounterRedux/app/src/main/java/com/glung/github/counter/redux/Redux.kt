package com.glung.github.counter.redux

import com.redux.Store

sealed class CounterAction : com.redux.Action {
    object INIT : CounterAction()

    object INCREMENT : CounterAction()

    object DECREMENT : CounterAction()
}

data class Model(val counter: Int = 0) : com.redux.State

val reducer = { action: CounterAction, model: Model ->
    when (action) {
        is CounterAction.INIT -> model
        is CounterAction.INCREMENT -> Model(model.counter + 1)
        is CounterAction.DECREMENT -> Model(model.counter - 1)
    }
}

val store: Store<CounterAction, Model> = Store.create(Model(), reducer)
