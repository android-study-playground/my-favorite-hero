package com.br.myfavoritehero.base

import br.com.concrete.howdoyoufeel.extensions.getJson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.koin.core.KoinComponent
import java.net.HttpURLConnection

abstract class BaseInstrumentedTest : KoinComponent {

    val mockWebServer: MockWebServer = MockWebServer()

    init {
        mockWebServer.start(8080)
    }

    @After
    fun after() {
        mockWebServer.shutdown()
    }

    private fun setResponse(responseJson: String, code: Int) {
        val mockResponse = MockResponse()
                .setResponseCode(code)
                .setBody(responseJson.getJson())

        mockWebServer.enqueue(mockResponse)
    }

    fun mockResponse200(responseJson: String) {
        setResponse(responseJson, HttpURLConnection.HTTP_OK)
    }

    fun mockResponseError401() {
        setResponse("mock/common/return_error_unauthorized.json", HttpURLConnection.HTTP_UNAUTHORIZED)
    }
}