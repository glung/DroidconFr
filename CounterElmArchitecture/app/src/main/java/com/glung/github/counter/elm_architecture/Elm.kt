package com.glung.github.counter.elm_architecture

import android.app.Activity

fun dispatch(activity: Activity, action: Action = Action.INIT, model: Model = Model()) {
    val updatedModel = update(action, model)
    val dispatch = { newAction : Action -> dispatch(activity, newAction, updatedModel) }

    activity.setContentView(view(activity, updatedModel, dispatch))
}