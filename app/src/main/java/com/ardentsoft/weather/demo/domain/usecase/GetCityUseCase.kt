package com.ardentsoft.weather.demo.domain.usecase

import com.ardentsoft.weather.demo.domain.interactor.BaseUseCase
import com.ardentsoft.weather.demo.domain.repository.IDataRepo
import com.ardentsoft.weather.demo.domain.thread.IBackgroundThreadExecutor
import com.ardentsoft.weather.demo.domain.thread.IUIThread
import io.reactivex.Observable
import javax.inject.Inject

open class GetCityUseCase
@Inject constructor(
    private val dataRepo: IDataRepo,
    executor: IBackgroundThreadExecutor,
    thread: IUIThread
) : BaseUseCase<String, List<String>>(executor, thread) {

    override fun build(request: String): Observable<List<String>> {
        return dataRepo.getCityDetails()

    }

}
