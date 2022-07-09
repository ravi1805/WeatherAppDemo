package com.ardentsoft.weather.demo.di.modules

import com.ardentsoft.weather.demo.domain.thread.IBackgroundThreadExecutor
import com.ardentsoft.weather.demo.domain.thread.IBackgroundThreadExecutorImpl
import com.ardentsoft.weather.demo.domain.thread.IUIThread
import com.ardentsoft.weather.demo.domain.thread.UIThreadImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ExecuterModule {


    @Provides
    @Singleton
    internal fun provideThreadExecutor(threadExecuterImpl: IBackgroundThreadExecutorImpl): IBackgroundThreadExecutor {
        return threadExecuterImpl
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(iUiThreadImpl: UIThreadImpl): IUIThread {
        return iUiThreadImpl
    }


}
