package com.example.vikramgupta.sample.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import android.util.Log
import com.example.vikramgupta.sample.filterActiveCountries
import com.example.vikramgupta.sample.getDiff
import com.example.vikramgupta.sample.repo.CountriesRepo
import com.example.vikramgupta.sample.repo.model.CountriesResponse
import com.example.vikramgupta.sample.repo.model.CountryItem
import com.example.vikramgupta.sample.repo.model.db.Country
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

    private var countriesRepo: CountriesRepo? = null
    private var mediatorLiveData = MediatorLiveData<List<Country>>()

    init {
        countriesRepo = CountriesRepo.getInstance(getApplication())
        networkBoundResource()
    }

    /**
     * Check whether data returned from database is empty so that we perform a network instead
     */
    private fun networkBoundResource() {
        val countriesLiveData: LiveData<List<Country>>? = loadCountriesFromDB()
        mediatorLiveData.addSource(countriesLiveData as LiveData<List<Country>>) { result: List<Country>? ->
            if (result == null || result.isEmpty()) loadCountriesFromNetwork()
            else mediatorLiveData.value = result
        }
    }

    fun loadCountries(): LiveData<List<Country>>? {
        return mediatorLiveData
    }

    private fun loadCountriesFromDB(): LiveData<List<Country>>? {
        return countriesRepo?.loadCountriesFromDB()
    }

    fun onFavoriteClick(position: Int) {
        val country = mediatorLiveData.value!![position]
        if (country.favorite == 1) country.favorite = 0 else country.favorite = 1
        countriesRepo?.updateCountry(country)
    }

    private fun loadCountriesFromNetwork() {
        val request: Call<CountriesResponse>?  = countriesRepo?.loadCountriesFromNetwork(DEFAULT_PAGE, DEFAULT_PAGE_SIZE)
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
                handleNetworkResponse(response)
            }
        })
    }

    private fun handleNetworkResponse(response: Response<CountriesResponse>) {
        val countriesResponse = response.body()

        // We need to pick countries that have active disbursement options.
        val activeCountries = countriesResponse?.let { filterActiveCountries(it.items) as MutableList<CountryItem> }

        // Before saving them to DB we pick the items which are not present in DB and only insert those items. This reduces database operations.
        val diff = getDiff(activeCountries, mediatorLiveData.value)
        saveAll(diff)
    }

    /**
     * Save countries to DB
     */
    private fun saveAll(countries: List<Country>) {
        countriesRepo?.saveAll(countries)
    }

    /**
     * Call this when the server updates data so that we can update our database.
     * This could be called via a push notification to the device or it could be periodic update.
     * TODO: Move this logic somewhere else? Calling class might not have access to CountriesViewModel.
     */
    private fun onDataChangedOnServer() {
        loadCountriesFromNetwork()
    }
}