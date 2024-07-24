package com.dmovel.pc05_06_07_dmovel.service

import retrofit2.http.GET
import retrofit2.http.Query

interface OpenLibraryApiInterface {
    @GET("/search.json")
    suspend fun getDataByBookName(
        @Query("q") bookName: String
    ) : OpenLibraryResponse
}