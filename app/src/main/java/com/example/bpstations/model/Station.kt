package com.example.mybpstations.model

import java.io.Serializable

data class Station(
    val name: String,
    val address: String,
    val lat: Double,
    val lng: Double,
    val accepts_bp_fuel_card: Boolean,
    val open_24_hours: Boolean,
    val convenience_store: Boolean,
    val cv_fresh_food: Boolean,
    val distance: Double,
    val facilities: List<String>
) : Serializable