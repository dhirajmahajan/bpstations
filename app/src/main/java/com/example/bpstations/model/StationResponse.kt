package com.example.bpstations.model

import com.example.mybpstations.model.Station
import java.io.Serializable

data class StationResponse(
    val locations: List<Station>
) : Serializable
