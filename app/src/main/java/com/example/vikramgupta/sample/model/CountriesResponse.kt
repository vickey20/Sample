package com.example.vikramgupta.sample.model

/**
 *   Created by vikramgupta on 10/15/18.
 */

data class CountriesResponse(var items: List<Countries>)

data class Countries(var name: String,
                     var disbursementOptions: List<DisbursementOptions>)

data class DisbursementOptions(var id: String,
                               var mode: String)
