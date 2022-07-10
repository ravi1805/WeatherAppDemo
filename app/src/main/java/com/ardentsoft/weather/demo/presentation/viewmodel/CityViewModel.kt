package com.ardentsoft.weather.demo.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardentsoft.weather.demo.domain.interactor.DefaultObserver
import com.ardentsoft.weather.demo.domain.usecase.GetCityUseCase
import com.ardentsoft.weather.demo.netowrkservice.INetworkClientService
import com.ardentsoft.weather.demo.presentation.utils.*
import javax.inject.Inject

class CityViewModel @Inject constructor(
    private val getCityUseCase: GetCityUseCase,
    private val iNetworkClientService: INetworkClientService
) : ViewModel() {

    val TAG = "CityViewModel"

    val cityItemLiveData = MutableLiveData<Resource<List<String>>>()
    val errorMsgLiveData = MutableLiveData<String>()

    fun getAllCities() {
        cityItemLiveData.setLoading()
        if(iNetworkClientService.isMobileNetworkConnected()) {
            getCityUseCase.execute(CityDataObserver(), "")
        }else{
            errorMsgLiveData.postValue(AppUtils.noNetworkMsg)
        }
    }

    private inner class CityDataObserver : DefaultObserver<List<String>>() {
        override fun onNext(dataList: List<String>) {
            super.onNext(dataList)
            cityItemLiveData.setSuccess(dataList)
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
            cityItemLiveData.setError(exception.localizedMessage)
        }
    }

    override fun onCleared() {
        getCityUseCase.dispose()
        super.onCleared()
    }

}