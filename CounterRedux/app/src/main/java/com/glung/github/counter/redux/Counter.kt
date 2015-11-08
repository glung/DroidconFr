package com.glung.github.counter.redux

sealed class Action : com.redux.Action {
    object INIT : Action()
    object INCREMENT : Action()
    object DECREMENT : Action()
}

data class Model(val counter: Int = 0) : com.redux.State

val reducer = { action: Action, model: Model ->
    when (action) {
        is Action.INIT -> model
        is Action.INCREMENT -> Model(model.counter + 1)
        is Action.DECREMENT -> Model(model.counter - 1)
    }
}

