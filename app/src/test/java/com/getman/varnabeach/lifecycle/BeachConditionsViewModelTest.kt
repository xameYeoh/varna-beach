package com.getman.varnabeach.lifecycle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.getman.varnabeach.data.Repository
import com.getman.varnabeach.room.Beach
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class BeachConditionsViewModelTest {

    @Test
    fun requestConditions_callsRepository() {
        // given
        val mRepository = mock<Repository>()
        Mockito.`when`(mRepository.allBeaches)
            .thenReturn(MutableLiveData<List<Beach>>() as LiveData<List<Beach>>)
        val viewModel = BeachConditionsViewModel(mRepository)
        // when
        viewModel.requestConditions("", "") {}
        // then
        verify(mRepository).requestNewConditions(eq(""), eq(""), any())
    }
}
