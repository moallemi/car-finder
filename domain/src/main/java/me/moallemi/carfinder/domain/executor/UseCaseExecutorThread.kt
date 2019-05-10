package me.moallemi.carfinder.domain.executor

import io.reactivex.Scheduler

interface UseCaseExecutorThread {
    val scheduler: Scheduler
}