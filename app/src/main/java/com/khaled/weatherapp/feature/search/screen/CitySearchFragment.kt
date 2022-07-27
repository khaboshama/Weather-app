package com.khaled.weatherapp.feature.search.screen

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.location.*
import com.khaled.weatherapp.R
import com.khaled.weatherapp.common.BaseActivity
import com.khaled.weatherapp.common.BaseFragment
import com.khaled.weatherapp.common.network.ConnectivityUtils.isGpsEnabled
import com.khaled.weatherapp.common.network.ConnectivityUtils.isNetworkEnabled
import com.khaled.weatherapp.databinding.FragmentCitySearchBinding
import com.khaled.weatherapp.utils.ImageUtils
import kotlinx.android.synthetic.main.fragment_city_search_.*

class CitySearchFragment : BaseFragment<CitySearchViewModel>() {

    private var binding: FragmentCitySearchBinding? = null
    private var locationRequest: LocationRequest? = null
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    override val loadingView: View?
        get() = loadingProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.start()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentCitySearchBinding.inflate(inflater, container, false).also { binding = it }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setupObserver()
        setupLocationService()
    }

    private fun setListeners() {
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                viewModel.onCitySearchByNameChanged(s.toString())
            }

        })
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
                    weatherDescriptionTextView.text = it.weather.description
                    ImageUtils.loadImage(requireActivity(), it.weather.icon, R.drawable.ic_weather, weatherImage)
                    searchEditText.setText(it.name)
                    searchEditText.setSelection(searchEditText.length())
                    View.VISIBLE
                }
            }
        }
    }

    private fun setupLocationService() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        createLocationRequest()
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    viewModel.onLocationChanged(location)
                }
            }
        }
        handleStartLocationService()
    }

    private fun handleStartLocationService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((getCurrentActivity() as BaseActivity<*>).checkLocationPermissionIsNotGranted(requireContext())) {
                requestLocationPermission(LOCATION_REQUEST_CODE)
                return
            }
        }
        requestLocationUpdates()
    }

    private fun createLocationRequest() {
        LocationRequest.create().apply {
            interval = LOCATION_INTERVAL_REFRESH_TIME
            fastestInterval = LOCATION_INTERVAL_REFRESH_TIME
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        }.also { locationRequest = it }
        locationRequest?.smallestDisplacement = LOCATION_REFRESH_DISTANCE
    }

    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_REQUEST_CODE) {
            if ((getCurrentActivity() as BaseActivity<*>).checkAllPermissionGranted(grantResults)) {
                requestLocationUpdates()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        if (isNetworkEnabled() && isGpsEnabled().not()) return
        locationRequest?.let {
            fusedLocationClient.requestLocationUpdates(
                it,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    companion object {
        private const val LOCATION_INTERVAL_REFRESH_TIME = 1 * 60 * 1000L
        private const val LOCATION_REFRESH_DISTANCE = 500f
        private const val LOCATION_REQUEST_CODE = 1000
    }
}