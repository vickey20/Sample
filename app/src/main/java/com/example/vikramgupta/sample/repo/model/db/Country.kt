package com.example.vikramgupta.sample.repo.model.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.vikramgupta.sample.repo.model.DisbursementOptions
import com.example.vikramgupta.sample.repo.model.db.DatabaseConstants.TABLE_COUNTRIES
import com.google.gson.annotations.SerializedName

/**
 *   Created by vikramgupta on 10/17/18.
 */

@Entity(tableName = TABLE_COUNTRIES)
data class Country(@PrimaryKey var code: String,
                   var name: String,
                   var favorite: Int = 0)