package com.example.dictionaryrb.feature_dictionary.data.remote

import retrofit2.http.GET
import retrofit2.http.Path

interface DictApi {

    @GET("/api/v2/entries/en/{word}")
    suspend fun getWordInfo(
        @Path("word") word : String
    ) : List<WordInfoDto>

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/"
    }
}