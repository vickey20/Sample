package com.example.vikramgupta.sample.repo

import android.arch.lifecycle.LiveData
import android.content.Context
import com.example.vikramgupta.sample.repo.model.db.Country
import com.example.vikramgupta.sample.repo.model.db.CountryDao
import com.example.vikramgupta.sample.repo.model.db.AppDatabase
import com.example.vikramgupta.sample.repo.network.XoomNetworkService
import kotlinx.coroutines.experimental.GlobalScope
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 *   Created by vikramgupta on 10/17/18.
 */

class CountriesRepo {

    companion object {
        @Volatile private var instance: CountriesRepo? = null
        private var db: AppDatabase? = null
        private var countryDao: CountryDao? = null

        fun getInstance(context: Context): CountriesRepo {
            return instance?: synchronized(this) {
                instance?: CountriesRepo().also {
                    instance = it
                    db = AppDatabase.getDatabase(context.applicationContext)
                    countryDao = db?.countryDao()
                }
            }
        }
    }

    /* Database */
    fun loadCountriesFromDB(): LiveData<List<Country>>? = countryDao?.loadCountries()


    fun saveAll(countries: List<Country>) {
        GlobalScope.launch {
            async { countryDao?.insertAll(countries) }
        }
    }

    fun updateCountry(country: Country) {
        GlobalScope.launch {
            async { countryDao?.update(country) }
        }
    }

    /* Network */
    fun loadCountriesFromNetwork(page: Int, pageSize: Int) = XoomNetworkService.getCountries(page, pageSize)

    fun loadImageUrlForCountry(countryCode: String) = XoomNetworkService.loadImageUrlForCountry(countryCode)
}