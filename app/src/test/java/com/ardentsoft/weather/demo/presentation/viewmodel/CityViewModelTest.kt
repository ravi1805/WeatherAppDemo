package com.ardentsoft.weather.demo.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ardentsoft.weather.demo.domain.usecase.GetCityUseCase
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
class CityViewModelTest {
    @get:Rule
    var instantiationExecutoreRule = InstantTaskExecutorRule()

    private lateinit var cityViewModel: CityViewModel

    @Mock
    private lateinit var getCityUseCase: GetCityUseCase

    @Mock
    private lateinit var iNetworkClientService: INetworkClientService

    @Mock
    private lateinit var resultObserver: Observer<Resource<List<String>>>

    @Mock
    private lateinit var cityListObserver: TestObserver<List<String>>

    @Mock
    private lateinit var cityListObserable: Observable<List<String>>

    @Mock
    private lateinit var cityList:List<String>

    @Before
    fun setup() {
        cityViewModel = CityViewModel(getCityUseCase, iNetworkClientService)
        cityViewModel.cityItemLiveData.observeForever(resultObserver)
    }

    @Test
    fun testTagTrue(){
        assertTrue(cityViewModel.TAG == "CityViewModel")
    }

    @Test
    fun testTagFalse(){
        assertFalse(cityViewModel.TAG == "CityView")
    }


    @Test
    fun `handle city data observer`(){
        assertNotNull(cityViewModel.cityItemLiveData)
        assertTrue(cityViewModel.cityItemLiveData.hasObservers())
    }

    @Test
    fun `fetch city loading state`() {
        cityViewModel.getAllCities()
        verify(resultObserver).onChanged(Resource(ResourceState.LOADING))
    }

    @Test
    fun `fetch city item loading state with null data`() {
        cityViewModel.getAllCities()
        assert(cityViewModel.cityItemLiveData.getOrAwaitValue().state == ResourceState.LOADING)
        assert(cityViewModel.cityItemLiveData.getOrAwaitValue().data?.none() == null)
    }


    @Test
    fun `fetch city List Result State Not Empty`() {
        Mockito.`when`(getCityUseCase.build("")).thenReturn(Observable.just(cityList))
        cityListObserable = getCityUseCase.build("")
        cityListObserver = TestObserver()

        cityListObserable.subscribe(cityListObserver)

        cityListObserver.assertComplete()
            .assertNoErrors()
            .assertValue(cityList)

        assertNotNull(cityViewModel.cityItemLiveData.setSuccess(cityList))
        cityListObserver.onNext(cityList)

    }


}