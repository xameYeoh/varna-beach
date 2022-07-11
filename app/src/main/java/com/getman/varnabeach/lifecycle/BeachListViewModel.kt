package com.getman.varnabeach.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.getman.varnabeach.data.Repository
import com.getman.varnabeach.room.Beach
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BeachListViewModel @Inject constructor(
    repository: Repository
) : ViewModel() {
    val allBeaches: LiveData<List<Beach>> = repository.allBeaches
    private val selectedBeachMutable = MutableLiveData<Beach>()
    val selectedBeach: LiveData<Beach> get() = selectedBeachMutable

    fun select(selectedBeach: Beach) {
        selectedBeachMutable.value = selectedBeach
    }
}