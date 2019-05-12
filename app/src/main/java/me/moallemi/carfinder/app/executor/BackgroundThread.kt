package me.moallemi.carfinder.app.executor

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import me.moallemi.carfinder.domain.executor.UseCaseExecutorThread

class BackgroundThread : UseCaseExecutorThread {
    override val scheduler: Scheduler by lazy { Schedulers.io() }
}