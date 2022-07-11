package com.getman.varnabeach.lifecycle

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.getman.varnabeach.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeachConditionsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private var conditions: MutableLiveData<Map<String, String>> = MutableLiveData()
    val currentConditions get(): LiveData<Map<String, String>> = conditions

    fun requestConditions(
        lat: String?,
        lng: String?,
        listener: OnLoadingListener
    ) {
        if (lat != null && lng != null) {
            listener.onLoading()
            repository.requestNewConditions(lat, lng) { updateData() }
        }
    }

    private fun updateData() {
        conditions.value = repository.currentBeachConditions
    }
}