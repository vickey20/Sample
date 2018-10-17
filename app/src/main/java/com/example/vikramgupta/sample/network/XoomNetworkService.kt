package com.example.vikramgupta.sample.network

import com.example.vikramgupta.sample.model.CountriesResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *   Created by vikramgupta on 10/15/18.
 */

object XoomNetworkService {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://mobile.xoom.com/"
    const val COUNTRIES_API = "/catalog/v2/countries"

    private fun getRetrofit(): Retrofit {
        return retrofit?: Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .also { retrofit = it }
    }

    private fun getCountriesApi(): CountriesApi? {
        return getRetrofit()?.create(CountriesApi::class.java)
    }

    fun getCountries(page: Int, pageSize: Int): Call<CountriesResponse>? {
        return getCountriesApi()?.getCountries(page, pageSize)
    }
}