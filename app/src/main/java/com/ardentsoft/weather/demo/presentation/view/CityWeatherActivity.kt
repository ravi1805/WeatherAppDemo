package com.ardentsoft.weather.demo.presentation.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ardentsoft.weather.demo.R
import com.ardentsoft.weather.demo.domain.model.UIWeatherData
import com.ardentsoft.weather.demo.presentation.BaseActivity
import com.ardentsoft.weather.demo.presentation.utils.AppUtils
import com.ardentsoft.weather.demo.presentation.utils.Resource
import com.ardentsoft.weather.demo.presentation.utils.ResourceState
import com.ardentsoft.weather.demo.presentation.utils.ViewModelFactory
import com.ardentsoft.weather.demo.presentation.viewmodel.CityWeatherViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_city_weather_info.*
import kotlinx.android.synthetic.main.view_no_network.*
import javax.inject.Inject

class CityWeatherActivity : BaseActivity() {
    private lateinit var cityWeatherViewModel: CityWeatherViewModel

    @Inject
    lateinit var cityWeatherViewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_city_weather_info)
        cityWeatherViewModel =
            ViewModelProvider(this, cityWeatherViewModelFactory)[CityWeatherViewModel::class.java]
        cityWeatherViewModel.itemLiveData.observe(this, Observer { handleCityDataResponse(it) })
        cityWeatherViewModel.errorMsgLiveData.observe(this, Observer {
            showNetworkError(getString(R.string.no_network))
        })
        val city = intent.extras?.getString(AppUtils.item_key) ?: ""
        cityWeatherViewModel.getWeatherForCity(city)

    }


    /**
     * Handle response from view model observer
     */
    private fun handleCityDataResponse(resource: Resource<UIWeatherData>) {
        resource.let { resourceList ->
            hideNetworkError()
            when (resourceList.state) {

                ResourceState.LOADING -> {
                    showProgress()
                }
                ResourceState.SUCCESS -> {
                    hideProgress()
                    resourceList.data?.let { item ->
                        tvTemp.text = "Temperature : ${item.temp}\u00B0"
                        tvHumidity.text = "Humidity : ${item.humidity}\u00B0"
                    }
                }
                ResourceState.ERROR -> {
                    hideProgress()
                    showNetworkError(resourceList.message ?: "Something went wrong!")
                }
            }
        }
    }


    private fun showNetworkError(msg: String) {
        noNetworkLayout.visibility = View.VISIBLE
        noNetworkText.text = msg
        weatherDetails.visibility =View.GONE
    }

    private fun hideNetworkError() {
        noNetworkLayout.visibility = View.GONE
        weatherDetails.visibility =View.VISIBLE
    }

    private fun showProgress(){
        viewLoading.visibility=View.VISIBLE
    }

    private fun hideProgress(){
        viewLoading.visibility=View.GONE
    }
}