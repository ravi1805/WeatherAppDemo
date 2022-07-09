package com.ardentsoft.weather.demo.di.modules

import android.app.Application
import com.ardentsoft.weather.demo.netowrkservice.INetworkClientService
import com.ardentsoft.weather.demo.netowrkservice.NetworkClientFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    /*
   * The method returns the network client object
   * */
    @Singleton
    @Provides
    fun provideRetrofitNetworkService(application: Application): INetworkClientService {
        return NetworkClientFactory(application)
    }


}