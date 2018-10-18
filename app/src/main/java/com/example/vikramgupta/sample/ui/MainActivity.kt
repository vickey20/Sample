package com.example.vikramgupta.sample.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.vikramgupta.sample.R
import com.example.vikramgupta.sample.repo.model.Country

class MainActivity : AppCompatActivity(), CountriesFragment.OnFragmentInteractionListener {
    override fun onCountrySelected(country: Country) {
        TODO("not implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, CountriesFragment.newInstance())
                    .commit()
        }
    }
}
