package com.example.vikramgupta.sample.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.example.vikramgupta.sample.model.CountriesResponse
import com.example.vikramgupta.sample.model.Country
import com.example.vikramgupta.sample.network.XoomNetworkService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 *   Created by vikramgupta on 10/16/18.
 */

class CountriesViewModel(application: Application): AndroidViewModel(application) {
    companion object {
        private const val DEFAULT_PAGE = 1
        private const val DEFAULT_PAGE_SIZE = 100
    }

    private var countries = MutableLiveData<List<Country>>()

    init {
        countries = loadCountries()
    }

    fun getCountries(): MutableLiveData<List<Country>> {
        return countries
    }

    private fun loadCountries(): MutableLiveData<List<Country>> {
        var request: Call<CountriesResponse>?  = XoomNetworkService.getCountries(DEFAULT_PAGE, DEFAULT_PAGE_SIZE)
        request?.enqueue(object: Callback<CountriesResponse> {
            override fun onFailure(call: Call<CountriesResponse>, t: Throwable) {
                Log.d("CountriesViewModel", "Failed to load countries: $t.message")
                t.printStackTrace()
            }

            override fun onResponse(call: Call<CountriesResponse>, response: Response<CountriesResponse>) {
                if (!response.isSuccessful) {
                    Log.d("CountriesViewModel", "Response returned unsuccessful")
                    return
                }
                var countriesResponse = response.body()
                countries.postValue(countriesResponse?.items)
            }

        })
        return countries
    }


}