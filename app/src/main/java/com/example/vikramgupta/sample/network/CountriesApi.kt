package com.example.vikramgupta.sample.network

import com.example.vikramgupta.sample.model.CountriesResponse
import com.example.vikramgupta.sample.network.XoomNetworkService.COUNTRIES_API
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *   Created by vikramgupta on 10/15/18.
 */

interface CountriesApi {

    @GET(COUNTRIES_API)
    fun getCountries(@Query("page") page: Int, @Query("page_size") pageSize: Int): Call<CountriesResponse>
}