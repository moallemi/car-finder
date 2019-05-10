package me.moallemi.carfinder.domain.executor

import io.reactivex.Scheduler

interface PostExecutorThread {
    val scheduler: Scheduler
}