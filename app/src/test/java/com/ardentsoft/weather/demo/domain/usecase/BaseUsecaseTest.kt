package com.ardentsoft.weather.demo.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ardentsoft.weather.demo.domain.repository.IDataRepo
import com.ardentsoft.weather.demo.domain.thread.IBackgroundThreadExecutor
import com.ardentsoft.weather.demo.domain.thread.IUIThread
import org.junit.Rule
import org.junit.rules.ExpectedException
import org.mockito.Mock


open class BaseUsecaseTest {
    @get:Rule
    var instantiationExecutoreRule = InstantTaskExecutorRule()

    @Mock
    protected lateinit var mockPostExecutor: IUIThread

    @Mock
    protected lateinit var mockThreadExecutor: IBackgroundThreadExecutor

    @Mock
    protected lateinit var iDataRepo: IDataRepo

    @Mock
    protected lateinit var expectedException: ExpectedException
}
