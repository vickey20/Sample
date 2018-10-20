package com.example.vikramgupta.sample

import android.app.Application
import com.example.vikramgupta.sample.viewmodel.CountriesViewModel

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.runners.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class CountriesViewModelTest {

    private lateinit var viewModel: CountriesViewModel
    private lateinit var application: Application

    @Before
    fun init() {
        application = mock(Application::class.java)
        viewModel = CountriesViewModel(application)
    }
}
