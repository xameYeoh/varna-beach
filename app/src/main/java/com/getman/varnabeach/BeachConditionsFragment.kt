package com.getman.varnabeach

import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.getman.varnabeach.databinding.FragmentBeachDetailBinding
import com.getman.varnabeach.lifecycle.BeachConditionsViewModel
import com.getman.varnabeach.lifecycle.BeachListViewModel
import com.getman.varnabeach.recycler.MapAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BeachConditionsFragment : Fragment() {
    private var binding: FragmentBeachDetailBinding? = null
    private val beachListViewModel: BeachListViewModel by activityViewModels()
    private val beachConditionsViewModel: BeachConditionsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBeachDetailBinding.inflate(
            layoutInflater
        )
        bindViewsToBeachInfo()
        setObserverForViewModel()
        return binding?.root
    }

    private fun bindViewsToBeachInfo() {
        val beach = beachListViewModel.selectedBeach.value
        binding?.beachCard?.beachInformation?.cardImage?.setImageURI(Uri.parse(beach?.imageURI))
        binding?.beachCard?.beachInformation?.cardDescription?.text = beach?.description
        binding?.beachCard?.beachInformation?.cardTitle?.text = beach?.name
    }

    private fun setAdapterForRecycler(conditions: Map<String, String>) {
        val recyclerAdapter = MapAdapter(conditions)
        binding?.recyclerViewConditions?.adapter = recyclerAdapter
        binding?.recyclerViewConditions?.layoutManager = LinearLayoutManager(requireActivity())
    }

    private fun setObserverForViewModel() {
        val beach = beachListViewModel.selectedBeach.value
        beach.let {
            beachConditionsViewModel.getConditions(beach?.lat, beach?.lng) { displayLoadingInfo() }
                .observe(requireActivity()) { conditions: Map<String, String> ->
                    configureAndDisplayMapAndDisableLoadingInformation(
                        conditions
                    )
                }
        }


    }

    private fun displayLoadingInfo() {
        binding?.testView?.text = resources.getText(R.string.loading)
    }

    private fun configureAndDisplayMapAndDisableLoadingInformation(conditions: Map<String, String>) {
        var conditions = conditions
        conditions = makeMapReadable(conditions)
        setAdapterForRecycler(conditions)
        binding?.testView?.visibility = View.INVISIBLE
    }

    private fun makeMapReadable(conditions: Map<String, String>): Map<String, String> {
        val units = UnitFormat(resources)
        val readable: MutableMap<String, String> = HashMap()
        for (key in conditions.keys) {
            var value = conditions[key]
            var keyMap = key
            value = value + " " + units.getUnitFor(key)
            keyMap = UnitFormat.convertCamelCaseToNormal(key)
            readable[keyMap] = value
        }
        return readable
    }

    class UnitFormat(r: Resources) {
        fun getUnitFor(param: String): String? {
            var parameter = param
            parameter = parameter.uppercase()
            for (parameterType in UNITS.keys) {
                if (parameter.contains(parameterType)) return UNITS[parameterType]
            }
            return null
        }

        companion object {
            private val UNITS: MutableMap<String, String> = HashMap()

            @JvmStatic
            fun convertCamelCaseToNormal(text: String): String {
                val pascalCase = (text.substring(0, 1).uppercase()
                        + text.substring(1))
                val words = pascalCase.split("(?=[A-ZА-Я])".toRegex()).toTypedArray()
                val builder = StringBuilder()
                for (word in words) {
                    builder.append(word).append(" ")
                }
                return builder.toString()
            }
        }

        init {
            UNITS["SPEED"] = r.getString(R.string.speed_unit)
            UNITS["HEIGHT"] = r.getString(R.string.distance_unit)
            UNITS["TEMPERATURE"] = r.getString(R.string.temperature_unit)
        }
    }
}