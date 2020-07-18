package com.br.myfavoritehero.base

import com.br.myfavoritehero.extensions.getJson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit

abstract class BaseInstrumentedTest {

    private val mockWebServer: MockWebServer = MockWebServer()

    init {
        mockWebServer.start(8080)
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }

    private fun setResponse(responseJson: String, code: Int, delay: Long = 0) {
        val mockResponse = MockResponse()
                .setResponseCode(code)
                .setBody(responseJson.getJson())
                .setBodyDelay(delay, TimeUnit.MILLISECONDS)

        mockWebServer.enqueue(mockResponse)
    }

    fun mockResponse200(responseJson: String) {
        setResponse(responseJson, HttpURLConnection.HTTP_OK)
    }

    fun mockResponseError401() {
        setResponse("mock/common/return_error_401.json", HttpURLConnection.HTTP_UNAUTHORIZED)
    }

}