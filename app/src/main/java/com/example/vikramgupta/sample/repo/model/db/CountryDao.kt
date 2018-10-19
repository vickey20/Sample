package com.example.vikramgupta.sample.repo.model.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.vikramgupta.sample.repo.model.db.DatabaseConstants.FAVORITE
import com.example.vikramgupta.sample.repo.model.db.DatabaseConstants.NAME
import com.example.vikramgupta.sample.repo.model.db.DatabaseConstants.TABLE_COUNTRIES

/**
 *   Created by vikramgupta on 10/17/18.
 */

@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(country: Country)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(countries: List<Country>)

    @Update
    fun update(country: Country)

    @Delete
    fun delete(country: Country)

    @Query("SELECT * FROM $TABLE_COUNTRIES ORDER BY ($FAVORITE = 1) DESC, $NAME ASC")
    fun loadCountries(): LiveData<List<Country>>

    @Query("SELECT * FROM $TABLE_COUNTRIES WHERE ($FAVORITE = 1) ORDER BY $NAME ASC")
    fun getFavorites(): List<Country>
}