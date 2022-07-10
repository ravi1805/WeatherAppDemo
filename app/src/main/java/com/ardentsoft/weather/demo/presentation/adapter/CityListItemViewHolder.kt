package com.ardentsoft.weather.demo.presentation.adapter

import android.view.View
import android.widget.TextView
import com.ardentsoft.weather.demo.R
import com.ardentsoft.weather.demo.presentation.customeview.BaseViewHolder
import com.ardentsoft.weather.demo.presentation.customeview.OnRecyclerObjectClickListener


class CityListItemViewHolder(itemView: View) :
    BaseViewHolder<String, OnRecyclerObjectClickListener<String>>(itemView) {

    private val tvCityName = itemView.findViewById<TextView>(R.id.cityName)

    override fun onBind(
        item: String,
        listener: OnRecyclerObjectClickListener<String>,
        position: Int
    ) {
        tvCityName.text = item
        itemView.setOnClickListener {
            listener.onRowClicked(item, position)
        }
    }

}