package com.example.vikramgupta.sample.repo.model.db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.vikramgupta.sample.repo.model.DisbursementOptions
import com.example.vikramgupta.sample.repo.model.db.DatabaseConstants.TABLE_ACTIVE_COUNTRIES
import com.google.gson.annotations.SerializedName

/**
 *   Created by vikramgupta on 10/17/18.
 */

@Entity(tableName = TABLE_ACTIVE_COUNTRIES)
data class ActiveCountry(@PrimaryKey var code: String,
                         var name: String,
                         @SerializedName("disbursement_options") var disbursementOptions: List<DisbursementOptions>?,
                         var favorite: Int? = 0)