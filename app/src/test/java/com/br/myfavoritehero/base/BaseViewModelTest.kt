package com.br.myfavoritehero.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.br.myfavoritehero.di.PROPERTY_BASE_URL
import com.br.myfavoritehero.di.networkModule
import com.br.myfavoritehero.di.repositoryModule
import com.br.myfavoritehero.di.viewModelModule
import com.br.myfavoritehero.rules.RxImmediateSchedulerRule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.koin.core.KoinProperties
import org.koin.standalone.StandAloneContext
import org.koin.test.KoinTest
import java.net.HttpURLConnection

open class BaseViewModelTest : KoinTest {

    var mockServer: MockWebServer = MockWebServer()

    @get:Rule
    var rxRule = RxImmediateSchedulerRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    @Throws fun setUp() {
        StandAloneContext.startKoin(
                listOf(
                        networkModule,
                        repositoryModule,
                        viewModelModule
                ), properties = KoinProperties(extraProperties = mapOf(PROPERTY_BASE_URL to "http://localhost:8080/"))
        )

        mockServer.start(8080)
    }

    @After
    @Throws fun tearDown() {
        StandAloneContext.stopKoin()
        mockServer.shutdown()
    }

    fun getJson(path: String): String? {
        return this::class
                .java
                .classLoader?.getResource(
                path
        )?.readText()
    }

    private fun setResponse(responseJson: String, code: Int) {
        val mockResponse = MockResponse()
                .setResponseCode(code)
                .setBody(getJson(responseJson))
        mockServer.enqueue(mockResponse)
    }

    fun mockResponse200(responseJson: String) {
        setResponse(responseJson, HttpURLConnection.HTTP_OK)
    }

    fun mockResponseError401(responseJson: String) {
        setResponse(responseJson, HttpURLConnection.HTTP_UNAUTHORIZED)
    }

    fun mockResponseError404(responseJson: String) {
        setResponse(responseJson, HttpURLConnection.HTTP_NOT_FOUND)
    }

    fun mockResponseError405(responseJson: String) {
        setResponse(responseJson, HttpURLConnection.HTTP_BAD_METHOD)
    }

    fun mockResponseError409(responseJson: String) {
        setResponse(responseJson, HttpURLConnection.HTTP_CONFLICT)
    }

    fun validateURL(regex: String,password: String): Boolean {
        if (!password.contains(Regex(regex))) {
            return false
        }
        return true
    }

}