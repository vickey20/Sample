package com.example.vikramgupta.sample.model

import com.google.gson.annotations.SerializedName

/**
 *   Created by vikramgupta on 10/15/18.
 */

data class CountriesResponse(var items: List<Country>)

data class Country(var name: String,
                   var code: String,
                   @SerializedName("disbursement_options")
                   var disbursementOptions: List<DisbursementOptions>?)

data class DisbursementOptions(var id: String,
                               var mode: String)
