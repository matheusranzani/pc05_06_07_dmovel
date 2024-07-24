package com.dmovel.pc05_06_07_dmovel.service

import com.google.gson.annotations.SerializedName

data class OpenLibraryResponse (
    val docs: List<Docs>
)

data class Docs(
    @SerializedName("author_name")
    val authorName: List<String>,

    @SerializedName("first_publish_year")
    val firstPublishedYear: Int,

    @SerializedName("first_sentence")
    val firstSentence: List<String>?
)