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
    private var conditions: MutableLiveData<Map<String, String>>? = null

    fun getConditions(
        lat: String?,
        lng: String?,
        listener: OnLoadingListener
    ): LiveData<Map<String, String>> {
        if (conditions == null) {
            conditions = MutableLiveData()
            listener.onLoading()
            requestConditions(lat ?: "", lng ?: "")
        }
        return conditions!!
    }

    private fun requestConditions(lat: String, lng: String) {
        repository.requestNewConditions(lat, lng) { updateData() }
    }

    private fun updateData() {
        conditions!!.value = repository.getBeachConditions()
    }
}