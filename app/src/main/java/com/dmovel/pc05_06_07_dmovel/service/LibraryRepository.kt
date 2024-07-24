package com.dmovel.pc05_06_07_dmovel.service

import android.content.Context
import com.dmovel.pc05_06_07_dmovel.data.BookDatabase
import com.dmovel.pc05_06_07_dmovel.data.Book

class LibraryRepository(context: Context) {
    private val api = RetrofitInstance.api
    private val bookDao = BookDatabase.getDatabase(context).bookDao()

    suspend fun getDataByBookName(bookName: String): OpenLibraryResponse {
        val cachedBook = bookDao.getBookByName(bookName)

        return if (cachedBook != null) {
            OpenLibraryResponse(
                docs = listOf(
                    Docs(
                        authorName = listOf(cachedBook.authorName),
                        firstPublishedYear = cachedBook.firstPublishYear,
                        firstSentence = if (cachedBook.firstSentence.isNullOrBlank()) null else listOf(cachedBook.firstSentence)
                    )
                )
            )
        } else {
            val response = api.getDataByBookName(bookName)
            val firstDoc = response.docs.firstOrNull()

            if (firstDoc != null) {
                val book = Book(
                    authorName = firstDoc.authorName.firstOrNull() ?: "",
                    firstPublishYear = firstDoc.firstPublishedYear,
                    bookName = bookName,
                    firstSentence = firstDoc.firstSentence?.firstOrNull() ?: ""
                )
                bookDao.insertBook(book)
            }

            response
        }
    }

    suspend fun getAllBooks(): List<Book> {
        return bookDao.getAllBooks()
    }

    suspend fun getLastInsertedBook(): Book? {
        return bookDao.getLastInsertedBook()
    }
}