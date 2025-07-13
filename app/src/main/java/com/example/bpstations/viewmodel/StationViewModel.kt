package com.example.mybpstations.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mybpstations.model.Station
import com.example.bpstations.repository.StationRepository

class StationViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = StationRepository(application)
    private val all = repo.loadStations()

    private val _stations = MutableLiveData<List<Station>>(all)
    val stations: LiveData<List<Station>> = _stations

    // 🔽 Store current filter state
    var currentMaxKm: Int? = null
    var currentOpen24: Boolean = false
    var currentConvenienceStore: Boolean = false
    var currentFreshFood: Boolean = false
    var currentBpFuelCard: Boolean = false

    fun filterBy(
        maxDistance: Int? = null,
        onlyOpen24: Boolean = false,
        onlyWithStore: Boolean = false,
        onlyWithFreshFood: Boolean = false,
        bpFuelCard: Boolean = false
    ) {
        currentMaxKm = maxDistance
        currentOpen24 = onlyOpen24
        currentConvenienceStore = onlyWithStore
        currentFreshFood = onlyWithFreshFood
        currentBpFuelCard = bpFuelCard
        var filtered = all

        maxDistance?.let {
            filtered = filtered.filter { it.distance <= maxDistance }
        }

        if (onlyOpen24) filtered = filtered.filter { it.open_24_hours }
        if (onlyWithStore) filtered = filtered.filter { it.convenience_store }
        if (onlyWithFreshFood) filtered = filtered.filter { it.cv_fresh_food }
        if (bpFuelCard) filtered = filtered.filter { it.accepts_bp_fuel_card }

        _stations.value = filtered
    }


    fun resetFilters() {
        _stations.value = all
    }
}
