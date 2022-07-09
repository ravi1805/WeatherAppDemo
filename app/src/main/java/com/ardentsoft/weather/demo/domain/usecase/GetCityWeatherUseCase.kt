package com.ardentsoft.weather.demo.domain.usecase

import com.ardentsoft.weather.demo.domain.interactor.BaseUseCase
import com.ardentsoft.weather.demo.domain.model.UIWeatherData
import com.ardentsoft.weather.demo.domain.repository.IDataRepo
import com.ardentsoft.weather.demo.domain.thread.IBackgroundThreadExecutor
import com.ardentsoft.weather.demo.domain.thread.IUIThread
import io.reactivex.Observable
import javax.inject.Inject

open class GetCityWeatherUseCase
@Inject constructor(
    private val dataRepo: IDataRepo,
    executor: IBackgroundThreadExecutor,
    thread: IUIThread
) : BaseUseCase<String, UIWeatherData>(executor, thread) {

    override fun build(request: String): Observable<UIWeatherData> {
        return dataRepo.getCityWeatherDetails(request).flatMap { Observable.just(
        UIWeatherData(it.main.temp,it.main.temp_min,it.main.humidity))}
    }

}
