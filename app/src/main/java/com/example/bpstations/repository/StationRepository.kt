package com.example.bpstations.repository

import android.content.Context
import com.example.bpstations.model.StationResponse
import com.example.mybpstations.model.Station
import com.google.gson.Gson

class StationRepository(private val context: Context) {
    fun loadStations(): List<Station> {
        val json = context.assets.open("stations.json")
            .bufferedReader().use { it.readText() }
        return Gson().fromJson(json, StationResponse::class.java).locations
    }
}
