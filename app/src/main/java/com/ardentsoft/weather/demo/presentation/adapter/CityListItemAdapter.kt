package com.ardentsoft.weather.demo.presentation.adapter

import android.content.Context
import android.view.ViewGroup
import com.ardentsoft.weather.demo.R
import com.ardentsoft.weather.demo.domain.model.UIWeatherData
import com.ardentsoft.weather.demo.presentation.customeview.BaseViewHolder
import com.ardentsoft.weather.demo.presentation.customeview.CustomRecyclerViewAdapter
import com.ardentsoft.weather.demo.presentation.customeview.OnRecyclerObjectClickListener

class CityListItemAdapter(context: Context) :
    CustomRecyclerViewAdapter<String, OnRecyclerObjectClickListener<String>, BaseViewHolder<String, OnRecyclerObjectClickListener<String>>>(
        context
    ) {

    override fun getId(position: Int): String {
        return getItemAt(position)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<String, OnRecyclerObjectClickListener<String>> {
        return CityListItemViewHolder(
            itemView = inflate(
                R.layout.city_item,
                parent,
                false
            )
        )
        }

    }

