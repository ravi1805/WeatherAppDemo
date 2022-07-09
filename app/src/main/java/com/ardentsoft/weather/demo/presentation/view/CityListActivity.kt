package com.ardentsoft.weather.demo.presentation.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardentsoft.weather.demo.R
import com.ardentsoft.weather.demo.presentation.BaseActivity
import com.ardentsoft.weather.demo.presentation.adapter.CityListItemAdapter
import com.ardentsoft.weather.demo.presentation.customeview.OnRecyclerObjectClickListener
import com.ardentsoft.weather.demo.presentation.utils.*
import com.ardentsoft.weather.demo.presentation.viewmodel.CityViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_city.*
import kotlinx.android.synthetic.main.view_no_network.*
import javax.inject.Inject


class CityListActivity : BaseActivity() {
    private lateinit var cityListItemAdapter: CityListItemAdapter
    private lateinit var cityViewModel: CityViewModel

    @Inject
    lateinit var cityWeatherViewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_city)
        initView()
        cityViewModel =
            ViewModelProvider(this, cityWeatherViewModelFactory)[CityViewModel::class.java]
        cityViewModel.cityItemLiveData.observe(this, Observer { handleCityDataResponse(it) })
        cityViewModel.errorMsgLiveData.observe(this, Observer {
            showNetworkError(getString(R.string.no_network))
        })
        cityViewModel.getAllCities()
    }

    private fun initView() {
        cityListItemAdapter = CityListItemAdapter(this)
        cityItemListView.apply {
            adapter = cityListItemAdapter
            layoutManager = LinearLayoutManager(this@CityListActivity)
        }
        cityListItemAdapter.setListener(object :
            OnRecyclerObjectClickListener<String> {
            override fun onItemClicked(
                item: String,
                position: Int,
                operationID: Int
            ) {
                // Nothing to do

            }

            override fun onRowClicked(item: String, position: Int) {
                val bundle = Bundle()
                bundle.putString(AppUtils.item_key, item)
                Navigation.navigateScreen(
                    this@CityListActivity,
                    CityWeatherActivity::class.java,
                    false,
                    bundle
                )

            }
        })
    }

    /**
     * Handle response from view model observer
     */
    private fun handleCityDataResponse(resource: Resource<List<String>>) {
        resource.let { resourceList ->
            hideNetworkError()
            when (resourceList.state) {

                ResourceState.LOADING -> {
                }
                ResourceState.SUCCESS -> {
                    resourceList.data?.let { itemList ->
                        cityListItemAdapter.setItems(itemList)
                    }
                }
                ResourceState.ERROR -> {
                    showNetworkError(resourceList.message?: "Something went wrong!")
                }
            }
        }
    }


    private fun showNetworkError(msg: String){
        noNetworkLayout.visibility= View.VISIBLE
        noNetworkText.text = msg
    }

    private fun hideNetworkError(){
        noNetworkLayout.visibility= View.GONE
    }
}
