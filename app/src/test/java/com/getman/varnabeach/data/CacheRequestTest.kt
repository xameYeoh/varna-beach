package com.getman.varnabeach.data

import com.android.volley.*
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.*
import java.io.File

class CacheRequestTest {

    @Test
    fun whenNetworkResponseIsOk_returnsSuccessfulResponse() {
        // given
        val mock = mock<CacheRequest>()
        whenever(mock.parseNetworkResponse(any())).thenCallRealMethod()
        val response =
            javaClass.classLoader?.getResource("response.json")?.path?.let { File(it).readBytes() }
        val networkResponse = NetworkResponse(response)
        // when
        val result = mock.parseNetworkResponse(networkResponse)
        // then
        assertTrue(result.isSuccess)
    }

    @Test
    fun whenNetworkResponseIsOk_resultContainsResponse() {
        // given
        val mock = mock<CacheRequest>()
        whenever(mock.parseNetworkResponse(any())).thenCallRealMethod()
        val response =
            javaClass.classLoader?.getResource("response.json")?.path?.let { File(it).readBytes() }
        val networkResponse = NetworkResponse(response)
        // when
        val result = mock.parseNetworkResponse(networkResponse)
        // then
        assertTrue(result.result == networkResponse)
    }

    @Test
    fun whenNetworkResponseIsOk_entryContainsDataFromResponse() {
        // given
        val mock = mock<CacheRequest>()
        whenever(mock.parseNetworkResponse(any())).thenCallRealMethod()
        val response =
            javaClass.classLoader?.getResource("response.json")?.path?.let { File(it).readBytes() }
        val networkResponse = NetworkResponse(response)
        // when
        val result = mock.parseNetworkResponse(networkResponse)
        // then
        assertTrue(result.cacheEntry?.data.contentEquals(networkResponse.data))
    }
}