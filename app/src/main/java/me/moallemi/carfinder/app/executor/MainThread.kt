package me.moallemi.carfinder.app.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import me.moallemi.carfinder.domain.executor.PostExecutorThread

class MainThread : PostExecutorThread {
    override val scheduler: Scheduler by lazy { AndroidSchedulers.mainThread() }
}