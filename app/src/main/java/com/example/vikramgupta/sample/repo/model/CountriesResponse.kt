package com.example.vikramgupta.sample.repo.model

import com.google.gson.annotations.SerializedName

/**
 *   Created by vikramgupta on 10/15/18.
 */

data class CountriesResponse(var items: List<CountryItem>)

data class CountryItem(var code: String,
                   var name: String,
                   @SerializedName("disbursement_options")
                   var disbursementOptions: List<DisbursementOptions>?)

data class DisbursementOptions(var id: String,
                               var mode: String)
