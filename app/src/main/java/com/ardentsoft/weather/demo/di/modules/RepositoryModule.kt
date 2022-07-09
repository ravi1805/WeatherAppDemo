package com.ardentsoft.weather.demo.di.modules

import com.ardentsoft.weather.demo.data.datasource.IRemoteDataTransaction
import com.ardentsoft.weather.demo.data.remote.RemoteTransactionManager
import com.ardentsoft.weather.demo.data.repository.DataRepoImpl
import com.ardentsoft.weather.demo.domain.repository.IDataRepo
import com.ardentsoft.weather.demo.netowrkservice.INetworkClientService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteDataRepo(
        networkService: INetworkClientService
    ): IRemoteDataTransaction {
        return RemoteTransactionManager(networkService)
    }


    @Provides
    @Singleton
    fun provideDataRepo(
        iRemoteDataTransaction: IRemoteDataTransaction
    ): IDataRepo {
        return DataRepoImpl(iRemoteDataTransaction)
    }

}