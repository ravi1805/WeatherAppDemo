package com.ardentsoft.weather.demo

import android.app.Application
import com.ardentsoft.weather.demo.di.component.DaggerApplicationComponent
import com.ardentsoft.weather.demo.netowrkservice.INetworkClientService
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Android Main Application
 */
class MainApplication : Application() , HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
    @Inject
    lateinit var networkService: INetworkClientService

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
        initNetworkClient()
    }

    private fun initDagger() {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    private fun initNetworkClient() {
        networkService.setupNetworkClient(BuildConfig.URL)
    }


}
