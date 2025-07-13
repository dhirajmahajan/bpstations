package com.example.mybpstations.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.TextView
import com.example.bpstations.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FilterBottomSheetFragment(
    private val defaultMaxKm: Int?,
    private val defaultOpen24: Boolean,
    private val defaultStore: Boolean,
    private val defaultFreshFood: Boolean,
    private val defaultFuelCard: Boolean,
    val onApply: (maxDistance: Int?, onlyOpen24: Boolean, convStore: Boolean, convStoreServe: Boolean, bpFuelCard: Boolean) -> Unit
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.bottomsheet_filter, container, false)
    }

    override fun onViewCreated(v: View, s: Bundle?) {
        val seek = v.findViewById<SeekBar>(R.id.distanceSeekBar)
        val label = v.findViewById<TextView>(R.id.distanceLabel)
        val open24 = v.findViewById<CheckBox>(R.id.open24Checkbox)
        val convStore = v.findViewById<CheckBox>(R.id.convStoreCeckbox)
        val convStoreServe = v.findViewById<CheckBox>(R.id.convStoreServeCheckbox)
        val bpFuelCard = v.findViewById<CheckBox>(R.id.bpFuelCardCheckbox)
        val btnApply = v.findViewById<Button>(R.id.btnApply)
        val btnReset = v.findViewById<Button>(R.id.btnReset)

        // Set defaults
        seek.progress = defaultMaxKm ?: 500
        label.text = "${seek.progress} km"
        open24.isChecked = defaultOpen24
        convStore.isChecked = defaultStore
        convStoreServe.isChecked = defaultFreshFood
        bpFuelCard.isChecked = defaultFuelCard

        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, p: Int, _var: Boolean) {
                label.text = "Max distance: $p km"
            }
            override fun onStartTrackingTouch(sb: SeekBar?) {}
            override fun onStopTrackingTouch(sb: SeekBar?) {}
        })

        btnApply.setOnClickListener {
            val services = mutableListOf<String>()
            if (open24.isChecked) services.add("twenty_four_hour")
            if (convStore.isChecked) services.add("Convenience Store")
            if (convStoreServe.isChecked) services.add("Convenience Store with Hot & Fresh Food Service")
            if (bpFuelCard.isChecked) services.add("EV")
            onApply(seek.progress, open24.isChecked,convStore.isChecked, convStoreServe.isChecked,bpFuelCard.isChecked)
            dismiss()
        }

        btnReset.setOnClickListener {
            seek.progress = 500
            open24.isChecked = false
            convStore.isChecked = false
            convStoreServe.isChecked = false
            bpFuelCard.isChecked = false
            onApply(500,false, false, false, false)
            dismiss()
        }
    }
}
