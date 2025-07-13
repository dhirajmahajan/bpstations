package com.example.mybpstations.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bpstations.R
import com.example.mybpstations.model.Station

class StationAdapter(context: Context, items: List<Station>) :
    ArrayAdapter<Station>(context, 0, items.toMutableList()) {  // <-- copy to mutable list

    fun update(newItems: List<Station>) {
        clear()
        addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getView(pos: Int, convertView: View?, parent: ViewGroup): View {
        val v = convertView
            ?: LayoutInflater.from(context).inflate(R.layout.item_station, parent, false)
        val st = getItem(pos)!!

        v.findViewById<TextView>(R.id.stationName).text = st.name
        v.findViewById<TextView>(R.id.stationAddress).text = st.address
        v.findViewById<TextView>(R.id.stationDistance).text = String.format("%.1f km", st.distance)

        return v
    }
}
