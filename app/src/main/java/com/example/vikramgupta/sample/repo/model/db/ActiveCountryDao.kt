package com.example.vikramgupta.sample.repo.model.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.vikramgupta.sample.repo.model.db.DatabaseConstants.CODE
import com.example.vikramgupta.sample.repo.model.db.DatabaseConstants.FAVORITE
import com.example.vikramgupta.sample.repo.model.db.DatabaseConstants.TABLE_ACTIVE_COUNTRIES

/**
 *   Created by vikramgupta on 10/17/18.
 */

@Dao
interface ActiveCountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: ActiveCountry)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries: List<ActiveCountry>)

    @Update
    fun update(country: ActiveCountry)

    @Delete
    fun delete(country: ActiveCountry)

    @Query("SELECT * FROM $TABLE_ACTIVE_COUNTRIES ORDER BY ($FAVORITE = 1) ASC, $CODE ASC")
    fun loadActiveCountries(): LiveData<List<ActiveCountry>>
}