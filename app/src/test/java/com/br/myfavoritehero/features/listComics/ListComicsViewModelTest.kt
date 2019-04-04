package com.br.myfavoritehero.features.listComics

import com.br.myfavoritehero.base.BaseResponse
import com.br.myfavoritehero.base.BaseViewModelTest
import com.br.myfavoritehero.data.models.Comic
import com.br.myfavoritehero.data.models.ViewStateModel
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test
import org.koin.standalone.inject

class ListComicsViewModelTest: BaseViewModelTest(){

    private val comicsViewModel: ComicsViewModel by inject()
    private val characterId = "1010802"

    @Test
    fun createCorrectUrl(){
        val expectedPath = "\\/v1\\/public\\/comics\\?ts=[0-9]+&apikey=[a-z0-9]+&hash=[a-z0-9]+&characters=$characterId&limit=50&offset=0"
        mockResponse200("mock/list_comics/return_success.json")
        comicsViewModel.loadComics(characterId)
        val requestedPath = mockServer.takeRequest().path
        Assert.assertEquals(true, validateURL(expectedPath, requestedPath))
    }

    @Test
    fun checkListCharacterSuccess(){
        mockResponse200("mock/list_comics/return_success.json")
        val responseJson = getJson("mock/list_comics/return_success.json")
        val collectionType = object : TypeToken<BaseResponse<Comic>>() {}.type
        val responseObject: BaseResponse<Comic> = GsonBuilder()
                .create()
                .fromJson(responseJson, collectionType)

        val expected =
                ViewStateModel(status = ViewStateModel.Status.SUCCESS,
                        model = responseObject.data.results)

        var actual = ViewStateModel<ArrayList<Comic>>(status = ViewStateModel.Status.LOADING)

        comicsViewModel.getComics().observeForever {
            actual = ViewStateModel(model = it.model, status = it.status)
            assert(true)
        }

        comicsViewModel.loadComics(characterId)
        Assert.assertEquals(expected, actual)
    }
}