package com.ardentsoft.weather.demo.domain.interactor

import com.ardentsoft.weather.demo.domain.thread.IBackgroundThreadExecutor
import com.ardentsoft.weather.demo.domain.thread.IUIThread
import dagger.internal.Preconditions
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase<I, O>(
    private val backgroundExecutor: IBackgroundThreadExecutor,
    private val mainThread: IUIThread
) {
    private var compositeDisposable: CompositeDisposable
    abstract fun build(params: I): Observable<O>
    fun execute(observer: DisposableObserver<O>, params: I) {
        dispose()
        val observable = this.build(params)
            .subscribeOn(Schedulers.from(backgroundExecutor))
            .observeOn(mainThread.getMainThread())
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        synchronized(BaseUseCase::class.java) {
            if (!compositeDisposable.isDisposed) {

                // clear will also dispose
                compositeDisposable.clear()

                // renew object so that this use case can be executed again
                compositeDisposable = CompositeDisposable()
            }
        }
    }

    private fun addDisposable(disposable: Disposable) {
        synchronized(BaseUseCase::class.java) {
            Preconditions.checkNotNull(disposable)
            Preconditions.checkNotNull(compositeDisposable)
            compositeDisposable.add(disposable)
        }
    }

    init {
        compositeDisposable = CompositeDisposable()
    }
}