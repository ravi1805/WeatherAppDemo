package com.ardentsoft.weather.demo.domain.thread

import io.reactivex.Scheduler

interface IUIThread {
    fun getMainThread(): Scheduler
}
