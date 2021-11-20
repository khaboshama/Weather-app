package com.khaled.weatherapp.feature.search.screen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.khaled.weatherapp.R
import com.khaled.weatherapp.common.BaseFragment
import com.khaled.weatherapp.databinding.FragmentCitySearchBinding
import kotlinx.android.synthetic.main.fragment_city_search_.*

class CitySearchFragment : BaseFragment<CitySearchViewModel>() {

    private var binding: FragmentCitySearchBinding? = null

    override val loadingView: View?
        get() = loadingProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCitySearchBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.onCitySearchByNameChanged(s.toString())
            }

        })
        setupObserver()
    }

    private fun setupObserver() {
        with(viewModel) {
            dateView.observe(viewLifecycleOwner) { dateTextView.text = it }
            timeView.observe(viewLifecycleOwner) { timeTextView.text = it }
            showErrorView.observe(viewLifecycleOwner) {
                errorNotFoundTextView.visibility = if (it == null) {
                    View.GONE
                } else {
                    errorNotFoundTextView.text = it
                    View.VISIBLE
                }
            }

            cityWeatherItemView.observe(viewLifecycleOwner) {
                weatherContainer.visibility = if (it == null) {
                    View.GONE
                } else {
                    temperatureTextView.text = it.main.temp
                    maxTempTextView.text = resources.getString(R.string.max_temp_formatted, it.main.maxTemp)
                    minTempTextView.text = resources.getString(R.string.min_temp_formatted, it.main.minTemp)
                    windTempTextView.text = resources.getString(R.string.wind_formatted, it.wind.speed)
                    visibilityValueTextView.text = it.visibility
                    pressureValueTextView.text = it.main.pressure
                    humidityValueTextView.text = it.main.humidity
                    View.VISIBLE
                }
            }
        }
    }
}