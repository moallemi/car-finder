package me.moallemi.carfinder.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import me.moallemi.carfinder.model.Resource

fun <T : Any, L : LiveData<Resource<T>>> LifecycleOwner.observe(liveData: L, body: (Resource<T>?) -> Unit) =
    liveData.observe(this, Observer(body))