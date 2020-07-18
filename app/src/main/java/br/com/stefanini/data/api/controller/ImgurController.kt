package br.com.stefanini.data.api.controller

import br.com.stefanini.data.response.ImgurDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ImgurController {

    @GET("gallery/search")
    fun searchImages(@Query("q") query: String): Call<ImgurDataResponse>
}