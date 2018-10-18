package com.example.vikramgupta.sample.repo

import android.arch.lifecycle.LiveData
import android.content.Context
import com.example.vikramgupta.sample.repo.model.db.ActiveCountry
import com.example.vikramgupta.sample.repo.model.db.ActiveCountryDao
import com.example.vikramgupta.sample.repo.model.db.AppDatabase

/**
 *   Created by vikramgupta on 10/17/18.
 */

class CountriesRepo {

    companion object {
        @Volatile private var instance: CountriesRepo? = null
        private lateinit var db: AppDatabase

        fun getInstance(context: Context): CountriesRepo {
            return instance?: synchronized(this) {
                instance?: CountriesRepo().also {
                    instance = it
                    db = AppDatabase.getDatabase(context)
                }
            }
        }
    }

    private var activeCountryDao: ActiveCountryDao
    private var activeCountries: LiveData<List<ActiveCountry>>

    init {
        activeCountryDao = db.activeCountryDao()
        activeCountries = activeCountryDao.loadActiveCountries()
    }

    private fun loadActiveCountries(): LiveData<List<ActiveCountry>> {
        return activeCountryDao.loadActiveCountries()
    }

    private fun saveActiveCountries(activeCountries: List<ActiveCountry>) {
        activeCountryDao.insertAll(activeCountries)
    }

    private fun updateCountry(country: ActiveCountry) {
        activeCountryDao.update(country)
    }
}