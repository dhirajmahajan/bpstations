package com.example.bpstations.ui

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.bpstations.R
import com.example.mybpstations.model.Station
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class StationDetailBottomSheet(private val station: Station) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dialog_station_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nameView = view.findViewById<TextView>(R.id.tvName)
        val addressView = view.findViewById<TextView>(R.id.tvAddress)
        val distanceView = view.findViewById<TextView>(R.id.tvDistance)
        val facilitiesLayout = view.findViewById<LinearLayout>(R.id.facilitiesLayout)

        nameView.text = getBoldLabelSpannable("",station.name)
        addressView.text = getBoldLabelSpannable("Address: " ,station.address)
        distanceView.text = getBoldLabelSpannable("Distance: ", String.format("%.1f km", station.distance))

        facilitiesLayout.removeAllViews()
        station.facilities?.forEach { facility ->
            val textView = TextView(requireContext()).apply {
                text = "• ${facility.replace('_', ' ').replaceFirstChar { it.uppercase() }}"
                textSize = 14f
                setPadding(8, 4, 8, 4)
            }
            facilitiesLayout.addView(textView)
        }
    }
}

fun getBoldLabelSpannable(label: String, value: String): SpannableString {
    val fullText = "$label$value"
    return SpannableString(fullText).apply {
        setSpan(
            StyleSpan(Typeface.BOLD),
            0,
            label.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        // Increase the label size (e.g. 1.2x larger)
        setSpan(
            RelativeSizeSpan(1.2f),
            0,
            label.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}
