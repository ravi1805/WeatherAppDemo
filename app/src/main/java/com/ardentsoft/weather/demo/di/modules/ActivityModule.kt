package com.ardentsoft.weather.demo.di.modules

import com.ardentsoft.weather.demo.presentation.SplashActivity
import com.ardentsoft.weather.demo.presentation.view.CityListActivity
import com.ardentsoft.weather.demo.presentation.view.CityWeatherActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector()
    abstract fun contributeStateActivity(): CityListActivity

    @ContributesAndroidInjector()
    abstract fun contributeStateWeatherActivity(): CityWeatherActivity

}