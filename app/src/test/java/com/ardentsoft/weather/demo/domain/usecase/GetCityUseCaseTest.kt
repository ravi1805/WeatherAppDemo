package com.ardentsoft.weather.demo.domain.usecase

import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCityUseCaseTest : BaseUsecaseTest() {

    private lateinit var getCityUseCase: GetCityUseCase

    @Mock
    private lateinit var cityListObserver: TestObserver<List<String>>

    @Mock
    private lateinit var cityListObservable: Observable<List<String>>
    private val listOfCity = listOf<String>(
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
    @Before
    fun setup() {
        getCityUseCase = GetCityUseCase(iDataRepo, mockThreadExecutor, mockPostExecutor)
        expectedException = ExpectedException.none()
    }

    @Test
    fun `test data on success call`() {
        `when`(getCityUseCase.build("")).thenReturn(
            Observable.just(
                listOfCity
            )
        )
        cityListObservable = getCityUseCase.build("")
        cityListObserver = TestObserver()
        cityListObservable.subscribe(cityListObserver)
        cityListObserver.assertComplete().assertNoErrors().assertValue(listOfCity)
    }

    @Test
    fun `test result on happy case`() {
        getCityUseCase.build("")
        verify(iDataRepo).getCityDetails()
        Mockito.verifyNoMoreInteractions(iDataRepo)
        Mockito.verifyZeroInteractions(mockThreadExecutor)
        Mockito.verifyZeroInteractions(mockPostExecutor)
    }

    @Test
    fun `test result on failure case`() {
        expectedException.expect(NullPointerException::class.java)
        getCityUseCase.build("")
    }
}