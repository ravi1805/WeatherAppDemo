package com.ardentsoft.weather.demo.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ardentsoft.weather.demo.data.datasource.IRemoteDataTransaction
import com.ardentsoft.weather.demo.data.remote.response.WeatherData
import com.ardentsoft.weather.demo.data.remote.response.WeatherDataApiResponse
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class DataRepoImplTest {

    @get:Rule
    var instantiationExecutoreRule = InstantTaskExecutorRule()

    private lateinit var dataRepoImpl: DataRepoImpl

    @Mock
    private lateinit var iRemoteDataTransaction: IRemoteDataTransaction

    @Before
    fun setUp() {
        dataRepoImpl = DataRepoImpl(iRemoteDataTransaction)
    }
    val listOfCity = listOf<String>(
        "Bangalore",
        "Chennai",
        "Hyderabad",
        "New Delhi",
        "Pune",
        "Mumbai",
        "Indore",
        "Gurgaon",
        "Kalyann"
    )
    private val uiWeatherData = WeatherDataApiResponse(WeatherData("100","100","150"),"Hyderabad")


    @Test
    fun `get city list`(){
        `when`(iRemoteDataTransaction.getCityList()).thenReturn(Observable.just(listOfCity))
        val result = dataRepoImpl.getCityDetails()
        assertEquals(9, result.blockingFirst().size)
    }

    @Test
    fun `get weather details for city`(){
        `when`(iRemoteDataTransaction.getCityWeatherDetails("Hyderabad")).thenReturn(Observable.just(uiWeatherData))
        val result = dataRepoImpl.getCityWeatherDetails("Hyderabad")
        assertEquals("Hyderabad", result.blockingFirst().name)
    }

    @Test
    fun `test city name`(){
        `when`(iRemoteDataTransaction.getCityWeatherDetails("Hyderabad")).thenReturn(Observable.just(uiWeatherData))
        val result = dataRepoImpl.getCityWeatherDetails("Hyderabad")
        assertEquals("Hyderabad", result.blockingFirst().name)
    }
    @Test
    fun `test city temp`(){
        `when`(iRemoteDataTransaction.getCityWeatherDetails("Hyderabad")).thenReturn(Observable.just(uiWeatherData))
        val result = dataRepoImpl.getCityWeatherDetails("Hyderabad")
        assertEquals("100", result.blockingFirst().main.temp)
    }

    @Test
    fun `test city max temp`(){
        `when`(iRemoteDataTransaction.getCityWeatherDetails("Hyderabad")).thenReturn(Observable.just(uiWeatherData))
        val result = dataRepoImpl.getCityWeatherDetails("Hyderabad")
        assertEquals("150", result.blockingFirst().main.temp_max)
    }
}