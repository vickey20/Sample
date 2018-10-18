package com.example.vikramgupta.sample.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.vikramgupta.sample.R
import com.example.vikramgupta.sample.ui.adapter.CountriesAdapter
import com.example.vikramgupta.sample.repo.model.Country
import com.example.vikramgupta.sample.viewmodel.CountriesViewModel

import kotlinx.android.synthetic.main.fragment_countries.*

/**
 * Created by vikramgupta on 10/16/18.
 */
class CountriesFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null

    private var adapter: CountriesAdapter? = null

    private lateinit var viewModel: CountriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_countries, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()

        viewModel = ViewModelProviders.of(this).get(CountriesViewModel::class.java)
        viewModel.getCountries().observe(this, Observer { countries -> countries?.let { adapter?.updateList(it) } })
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL))
        adapter = CountriesAdapter()
        recyclerView.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onCountrySelected(country: Country)
    }

    companion object {
        @JvmStatic
        fun newInstance() = CountriesFragment()
    }
}
