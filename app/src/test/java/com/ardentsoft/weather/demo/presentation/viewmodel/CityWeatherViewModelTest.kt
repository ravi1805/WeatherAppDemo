package com.ardentsoft.weather.demo.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ardentsoft.weather.demo.domain.model.UIWeatherData
import com.ardentsoft.weather.demo.domain.usecase.GetCityWeatherUseCase
import com.ardentsoft.weather.demo.netowrkservice.INetworkClientService
import com.ardentsoft.weather.demo.presentation.utils.Resource
import com.ardentsoft.weather.demo.presentation.utils.ResourceState
import com.ardentsoft.weather.demo.presentation.utils.setSuccess
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CityWeatherViewModelTest {
    @get:Rule
    var instantiationExecutoreRule = InstantTaskExecutorRule()

    private lateinit var cityWeatherViewModel: CityWeatherViewModel

    @Mock
    private lateinit var getCityWeatherUseCase: GetCityWeatherUseCase

    @Mock
    private lateinit var iNetworkClientService: INetworkClientService

    @Mock
    private lateinit var resultObserver: Observer<Resource<UIWeatherData>>

    @Mock
    private lateinit var cityWeatherListObserver: TestObserver<UIWeatherData>

    @Mock
    private lateinit var cityWeatherListObserable: Observable<UIWeatherData>

    private val uiWeatherData = UIWeatherData("100","100","150","Bangalore")

    private val cityReq="New Delhi"
    @Before
    fun setup() {
        cityWeatherViewModel = CityWeatherViewModel(getCityWeatherUseCase, iNetworkClientService)
        cityWeatherViewModel.itemLiveData.observeForever(resultObserver)
    }

    @Test
    fun testTagTrue(){
        assertTrue(cityWeatherViewModel.TAG == "CityWeatherViewModel")
    }

    @Test
    fun testTagFalse(){
        assertFalse(cityWeatherViewModel.TAG == "CityWeatherView")
    }


    @Test
    fun `handle city data observer`(){
        assertNotNull(cityWeatherViewModel.itemLiveData)
        assertTrue(cityWeatherViewModel.itemLiveData.hasObservers())
    }

    @Test
    fun `fetch weather detail loading state`() {
        cityWeatherViewModel.getWeatherForCity(cityReq)
        verify(resultObserver).onChanged(Resource(ResourceState.LOADING))
    }

    @Test
    fun `fetch weather detail loading state with null data`() {
        cityWeatherViewModel.getWeatherForCity(cityReq)
        assert(cityWeatherViewModel.itemLiveData.getOrAwaitValue().state == ResourceState.LOADING)
    }


    @Test
    fun `fetch city List Result State Not Empty`() {

        Mockito.`when`(getCityWeatherUseCase.build(cityReq)).thenReturn(Observable.just(uiWeatherData))
        cityWeatherListObserable = getCityWeatherUseCase.build(cityReq)
        cityWeatherListObserver = TestObserver()

        cityWeatherListObserable.subscribe(cityWeatherListObserver)

        cityWeatherListObserver.assertComplete()
            .assertNoErrors()
            .assertValue(uiWeatherData)

        assertNotNull(cityWeatherViewModel.itemLiveData.setSuccess(uiWeatherData))
        cityWeatherListObserver.onNext(uiWeatherData)

    }


}