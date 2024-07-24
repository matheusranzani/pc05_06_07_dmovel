package com.dmovel.pc05_06_07_dmovel.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val authorName: String,
    val firstPublishYear: Int,
    val bookName: String,
    val firstSentence: String?
)