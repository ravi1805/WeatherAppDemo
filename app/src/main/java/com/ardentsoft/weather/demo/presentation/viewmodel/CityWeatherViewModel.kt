package com.ardentsoft.weather.demo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardentsoft.weather.demo.domain.interactor.DefaultObserver
import com.ardentsoft.weather.demo.domain.model.UIWeatherData
import com.ardentsoft.weather.demo.domain.usecase.GetCityWeatherUseCase
import com.ardentsoft.weather.demo.netowrkservice.INetworkClientService
import com.ardentsoft.weather.demo.presentation.utils.*
import javax.inject.Inject

class CityWeatherViewModel @Inject constructor(
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    private val iNetworkClientService: INetworkClientService
) : ViewModel() {

    val TAG = "CityWeatherViewModel"

    val itemLiveData = MutableLiveData<Resource<UIWeatherData>>()
    val errorMsgLiveData = MutableLiveData<String>()

    fun getWeatherForCity(cityName: String) {
        itemLiveData.setLoading()
        if (iNetworkClientService.isMobileNetworkConnected()) {
            getCityWeatherUseCase.execute(WeatherDataObserver(), cityName)
        } else {
            errorMsgLiveData.postValue(AppUtils.noNetworkMsg)
        }
    }

    private inner class WeatherDataObserver : DefaultObserver<UIWeatherData>() {
        override fun onNext(dataList: UIWeatherData) {
            super.onNext(dataList)
            itemLiveData.setSuccess(dataList)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            itemLiveData.setError(exception.message)
        }
    }

    override fun onCleared() {
        getCityWeatherUseCase.dispose()
        super.onCleared()
    }

}