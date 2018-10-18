package com.example.vikramgupta.sample.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.vikramgupta.sample.R
import com.example.vikramgupta.sample.repo.model.Country
import com.example.vikramgupta.sample.repo.network.XoomNetworkService
import kotlinx.android.synthetic.main.countries_list_item.view.*

/**
 *   Created by vikramgupta on 10/16/18.
 */
class CountriesAdapter: RecyclerView.Adapter<CountriesAdapter.ViewHolder>() {

    private var countries = mutableListOf<Country>()

    fun updateList(countries: List<Country>) {
        this.countries.clear()
        this.countries.addAll(countries)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(country: Country) {
            itemView.name.text = country.name
            itemView.image.loadFlag(country.code)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.countries_list_item))
    }

    override fun getItemCount(): Int {
        return countries.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    private fun ViewGroup.inflate(layoutId: Int): View {
        return LayoutInflater.from(this.context).inflate(layoutId, this, false)
    }

    private fun ImageView.loadFlag(countryCode: String) {
        XoomNetworkService.loadImageUrlForCountry(countryCode).into(this)
    }


}