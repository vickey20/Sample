package com.example.vikramgupta.sample.model

/**
 *   Created by vikramgupta on 10/15/18.
 */

data class CountriesResponse(var items: List<Country>)

data class Country(var name: String,
                   var code: String,
                   var disbursementOptions: List<DisbursementOptions>)

data class DisbursementOptions(var id: String,
                               var mode: String)
