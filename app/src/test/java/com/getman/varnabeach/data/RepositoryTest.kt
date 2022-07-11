package com.getman.varnabeach.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.NetworkResponse
import com.android.volley.Request
import com.getman.varnabeach.lifecycle.OnChangeConditionListener
import com.getman.varnabeach.room.Beach
import com.getman.varnabeach.room.BeachDAO
import com.getman.varnabeach.util.ErrorHandler
import com.getman.varnabeach.util.ErrorToastDisplayer
import org.junit.Assert.*

import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.kotlin.*
import java.io.File

class RepositoryTest {
    @Test
    fun requestNewConditions_shouldAddToRequestQueue() {
        // given
        val mDao = mock<BeachDAO> {
            on(mock.allOrderByName) doReturn MutableLiveData<List<Beach>>() as LiveData<List<Beach>>
        }
        val mHelper = mock<VolleyHelper>()
        val mErrorHandler = mock<ErrorHandler>()
        val repository = Repository(mDao, mHelper, mErrorHandler)
        val mListener = mock<OnChangeConditionListener>()
        // when
        repository.requestNewConditions("", "", mListener)
        // then
        verify(mHelper).addToRequestQueue(any<Request<Any>>())
    }
}