package com.example.vikramgupta.sample.repo.network

import com.example.vikramgupta.sample.repo.model.CountriesResponse
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors

/**
 *   Created by vikramgupta on 10/15/18.
 */

object XoomNetworkService {
    private var retrofit: Retrofit? = null
    private const val BASE_URL = "https://mobile.xoom.com/"
    const val COUNTRIES_API = "/catalog/v2/countries"
    private const val COUNTRY_FLAG_BASE_URL = "https://www.countryflags.io/"
    private const val FLAG_FLAT_64 = "/flat/64.png"

    private fun getRetrofit(): Retrofit {
        return retrofit?: Retrofit.Builder()
                .baseUrl(BASE_URL)
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .also { retrofit = it }
    }

    private fun getCountriesApi(): CountriesApi? {
        return getRetrofit().create(CountriesApi::class.java)
    }

    fun getCountries(page: Int, pageSize: Int): Call<CountriesResponse>? {
        return getCountriesApi()?.getCountries(page, pageSize)
    }

    private fun getPicasso(): Picasso = Picasso.get()

    private fun getFlagUrl(countryCode: String) = "$COUNTRY_FLAG_BASE_URL$countryCode$FLAG_FLAT_64"

    fun loadImageUrlForCountry(countryCode: String) = getPicasso().load(getFlagUrl(countryCode))!!
}