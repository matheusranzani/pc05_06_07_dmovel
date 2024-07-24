package com.dmovel.pc05_06_07_dmovel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dmovel.pc05_06_07_dmovel.data.Book
import com.dmovel.pc05_06_07_dmovel.service.LibraryRepository
import kotlinx.coroutines.launch
import java.util.Locale

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = LibraryRepository(application)
    var books by mutableStateOf(listOf<Book>())
    var lastSearchedBook by mutableStateOf<Book?>(null)

    var screenLabel by mutableStateOf("")
    var loading by mutableStateOf(false)

    fun getData(book: String) {
        val bookName = if (book.trim().isEmpty()) "harry potter" else book
        screenLabel = ""
        loading = true

        viewModelScope.launch {
            try {
                val response = repository.getDataByBookName(bookName.trim().lowercase())
                val label = "Author name: %s\nFirst publish year: %d"

                val doc = response.docs[0]

                val authorName = doc.authorName[0]
                val firstPublishYear = doc.firstPublishedYear

                screenLabel = String.format(Locale.US, label, authorName, firstPublishYear)
            } catch (e: Exception) {
                screenLabel = "Error: " + e.message
            } finally {
                loading = false
            }
        }
    }

    fun getBooks() {
        viewModelScope.launch {
            books = repository.getAllBooks()
        }
    }

    fun getLastBookSearched() {
        viewModelScope.launch {
            lastSearchedBook = repository.getLastInsertedBook()
        }
    }
}
