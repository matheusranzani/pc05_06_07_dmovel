package com.dmovel.pc05_06_07_dmovel.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BookDao {
    @Query("SELECT * FROM books WHERE bookName = :bookName LIMIT 1")
    suspend fun getBookByName(bookName: String): Book?

    @Insert
    suspend fun insertBook(book: Book)

    @Query("SELECT * FROM books")
    suspend fun getAllBooks(): List<Book>

    @Query("SELECT * FROM books ORDER BY id DESC LIMIT 1")
    suspend fun getLastInsertedBook(): Book?
}