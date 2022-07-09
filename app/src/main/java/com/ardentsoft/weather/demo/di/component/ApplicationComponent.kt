package com.ardentsoft.weather.demo.di.component

import android.app.Application
import com.ardentsoft.weather.demo.MainApplication
import com.ardentsoft.weather.demo.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModuleDI::class,
        NetworkModule::class,
        ExecuterModule::class,
        RepositoryModule::class]
)

interface ApplicationComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
    fun inject(application: MainApplication)
}
