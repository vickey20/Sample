package com.example.vikramgupta.sample

import com.example.vikramgupta.sample.repo.model.CountryItem
import com.example.vikramgupta.sample.repo.model.db.Country
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 *   Created by vikramgupta on 10/20/18.
 */
@RunWith(JUnit4::class)
class UtilTest {
    @Test
    fun getDiff_shouldReturnCorrectDiffWhenValidLists() {
        val listA = mutableListOf<CountryItem>()
        val listB = mutableListOf<Country>()

        listA.add(CountryItem("AD", "Andorra", null))
        listA.add(CountryItem("BR", "Brazil", null))
        listA.add(CountryItem("DE", "Germany", null))
        listA.add(CountryItem("ES", "Spain", null))
        listA.add(CountryItem("FR", "France", null))

        listB.add(Country("AL", "Albania", 0))
        listB.add(Country("BR", "Brazil", 0))
        listB.add(Country("DE", "Germany", 1))
        listB.add(Country("EG", "Egypt", 0))

        val diff = mutableListOf<Country>()
        diff.add(Country("AD", "Andorra", 0))
        diff.add(Country("ES", "Spain", 0))
        diff.add(Country("FR", "France", 0))

        val result = getDiff(listA, listB)

        assertEquals(diff.size, result.size)
        assertEquals(diff, result)
    }

    @Test
    fun getDiff_shouldReturnEmptyListWhenListsAreIdentical() {
        val listA = mutableListOf<CountryItem>()
        val listB = mutableListOf<Country>()

        listA.add(CountryItem("AD", "Andorra", null))
        listA.add(CountryItem("BR", "Brazil", null))
        listA.add(CountryItem("DE", "Germany", null))
        listA.add(CountryItem("ES", "Spain", null))
        listA.add(CountryItem("FR", "France", null))

        listB.add(Country("AD", "Andorra", 0))
        listB.add(Country("BR", "Brazil", 0))
        listB.add(Country("DE", "Germany", 0))
        listB.add(Country("ES", "Spain", 0))
        listB.add(Country("FR", "France", 0))

        val result = getDiff(listA, listB)

        assertEquals(0, result.size)
    }

    @Test
    fun getDiff_shouldReturnAllWhenListBIsEmpty() {
        val listA = mutableListOf<CountryItem>()
        val listB = mutableListOf<Country>()

        listA.add(CountryItem("AD", "Andorra", null))
        listA.add(CountryItem("BR", "Brazil", null))
        listA.add(CountryItem("DE", "Germany", null))
        listA.add(CountryItem("ES", "Spain", null))
        listA.add(CountryItem("FR", "France", null))

        val diff = mutableListOf<Country>()

        diff.add(Country("AD", "Andorra", 0))
        diff.add(Country("BR", "Brazil", 0))
        diff.add(Country("DE", "Germany", 0))
        diff.add(Country("ES", "Spain", 0))
        diff.add(Country("FR", "France", 0))

        val result = getDiff(listA, listB)

        assertEquals(diff, result)
    }

    @Test
    fun getDiff_shouldReturnEmptyListWhenListAIsEmpty() {
        val listA = mutableListOf<CountryItem>()
        val listB = mutableListOf<Country>()

        listB.add(Country("AD", "Andorra", 0))
        listB.add(Country("BR", "Brazil", 0))
        listB.add(Country("DE", "Germany", 0))
        listB.add(Country("ES", "Spain", 0))
        listB.add(Country("FR", "France", 0))

        val result = getDiff(listA, listB)

        assertEquals(0, result.size)
    }

    @Test
    fun getDiff_shouldReturnEmptyListWhenEitherOrBothListsAreNull() {
        var listA: List<CountryItem>? = null
        var listB: List<Country>? = null
        var result = getDiff(listA, listB)
        assertEquals(0, result.size)

        listA = null
        listB = mutableListOf()
        result = getDiff(listA, listB)
        assertEquals(0, result.size)

        listA = mutableListOf()
        listB = null
        result = getDiff(listA, listB)
        assertEquals(0, result.size)
    }

    @Test
    fun filterActiveCountries_shouldReturnCorrectWhenValidList() {
        val countries = mutableListOf<CountryItem>()
        countries.add(CountryItem("AD", "Andorra", null))
        countries.add(CountryItem("BR", "Brazil", mutableListOf()))
        countries.add(CountryItem("DE", "Germany", mutableListOf()))
        countries.add(CountryItem("ES", "Spain", null))
        countries.add(CountryItem("FR", "France", mutableListOf()))

        val filtered = mutableListOf<CountryItem>()
        filtered.add(CountryItem("BR", "Brazil", mutableListOf()))
        filtered.add(CountryItem("DE", "Germany", mutableListOf()))
        filtered.add(CountryItem("FR", "France", mutableListOf()))

        val result = filterActiveCountries(countries)

        assertEquals(result.size, filtered.size)
        assertEquals(result, filtered)
    }

    @Test
    fun filterActiveCountries_shouldReturnEmptyWhenNoActiveCountries() {
        val countries = mutableListOf<CountryItem>()
        countries.add(CountryItem("AD", "Andorra", null))
        countries.add(CountryItem("BR", "Brazil", null))
        countries.add(CountryItem("DE", "Germany", null))
        countries.add(CountryItem("ES", "Spain", null))
        countries.add(CountryItem("FR", "France", null))

        val result = filterActiveCountries(countries)

        assertEquals(result.size, 0)
    }

    @Test
    fun filterActiveCountries_shouldReturnEmptyWhenListIsEmpty() {
        val countries = mutableListOf<CountryItem>()
        val result = filterActiveCountries(countries)
        assertEquals(result.size, 0)
    }
}