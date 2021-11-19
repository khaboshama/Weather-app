package com.khaled.weatherapp.feature.search.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khaled.weatherapp.common.BaseFragment
import com.khaled.weatherapp.databinding.FragmentCitySearchListBinding
import kotlinx.android.synthetic.main.fragment_city_search_list.*

class CitySearchFragment : BaseFragment<CitySearchViewModel>() {

    private var binding: FragmentCitySearchListBinding? = null

    override val loadingView: View?
        get() = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCitySearchListBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.onSearchChanged(s.toString())
            }

        })
        viewModel.cityWeatherItemView.observe(viewLifecycleOwner) {

        }
    }
}