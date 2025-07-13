package com.example.mybpstations

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.annotation.OptIn
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.bpstations.R
import com.example.bpstations.ui.StationDetailBottomSheet
import com.example.mybpstations.ui.StationAdapter
import com.example.mybpstations.ui.bottomsheet.FilterBottomSheetFragment
import com.example.mybpstations.viewmodel.StationViewModel
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.google.android.material.floatingactionbutton.FloatingActionButton


class StationListFragment : Fragment() {
    private val vm: StationViewModel by viewModels()
    private lateinit var fab: FloatingActionButton
    private var badgeDrawable: BadgeDrawable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_station_list, container, false)
    }

    @OptIn(ExperimentalBadgeUtils::class)
    override fun onViewCreated(v: View, s: Bundle?) {
        val list = v.findViewById<ListView>(R.id.listView)
        val fab = v.findViewById<FloatingActionButton>(R.id.fabFilter)
        val adapter = StationAdapter(requireContext(), emptyList())
        list.adapter = adapter

        vm.stations.observe(viewLifecycleOwner) { adapter.update(it) }

        list.setOnItemClickListener { _, _, i, _ ->
            val station = vm.stations.value!![i]
            StationDetailBottomSheet(station)
                .show(parentFragmentManager, "StationDetail")

        }

        fab.setOnClickListener {
            FilterBottomSheetFragment (
                defaultMaxKm = vm.currentMaxKm,
                defaultOpen24 = vm.currentOpen24,
                defaultStore = vm.currentConvenienceStore,
                defaultFreshFood = vm.currentFreshFood,
                defaultFuelCard = vm.currentBpFuelCard
            ){ maxKm, onlyOpen24, covStore, convStoreServe, bpFuelCard ->
                val noFiltersSet = (maxKm == null || maxKm <= 0) &&
                        !onlyOpen24 && !covStore && !convStoreServe && !bpFuelCard

                if (noFiltersSet) vm.resetFilters()
                else vm.filterBy(maxKm, onlyOpen24, covStore, convStoreServe, bpFuelCard)

                updateFilterBadge(fab)

            }.show(parentFragmentManager, "filter")
        }
    }
    @OptIn(com.google.android.material.badge.ExperimentalBadgeUtils::class)
    private fun updateFilterBadge(fab: FloatingActionButton) {
            val activeFilters = listOf(
                vm.currentMaxKm != null && vm.currentMaxKm!! < 500,
                vm.currentOpen24,
                vm.currentConvenienceStore,
                vm.currentFreshFood,
                vm.currentBpFuelCard
            ).count { it }

            if (activeFilters > 0) {
                if (badgeDrawable == null) {
                    badgeDrawable = BadgeDrawable.create(requireContext())
                    BadgeUtils.attachBadgeDrawable(badgeDrawable!!, fab)
                }
                badgeDrawable!!.number = activeFilters
                badgeDrawable!!.isVisible = true
            } else {
                badgeDrawable?.isVisible = false
            }
    }
}
