package com.example.vikramgupta.sample

import com.example.vikramgupta.sample.repo.model.CountryItem
import com.example.vikramgupta.sample.repo.model.db.Country

/**
 *   Created by vikramgupta on 10/20/18.
 */

/**
 * Do a diff between listA fetched from network and listB fetched from DB.
 * Return a list containing the items in listA not present in listB (DB)
 */
fun getDiff(listA: List<CountryItem>?, listB: List<Country>?): List<Country> {
    val codes = listB?.map { it.code }?.toSet()
    val diff = mutableListOf<Country>()
    listA?.forEach {
        if (codes == null || codes.isEmpty())
            diff.add(Country(it.code, it.name, 0))
        else {
            if (!codes.contains(it.code)) diff.add(Country(it.code, it.name, 0))
        }
    }
    return diff
}


/**
 * Filter out countries that don't have active disbursement options.
 */
fun filterActiveCountries(countries: List<CountryItem>): List<CountryItem> = countries.filter { it.disbursementOptions != null }