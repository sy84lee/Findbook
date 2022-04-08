package com.sy.searchbook

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sy.searchbook.model.doc
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PagingViewModel constructor (
    private val repository: PagingRepository
) : ViewModel() {

    private var queryString: String? = null
    var searchResult: Flow<PagingData<doc>>? = null

    fun searchRepository(queryString: String): Flow<PagingData<doc>> {
        val lastResult = searchResult
        if (this.queryString == queryString && lastResult != null) {
            return lastResult
        }
        this.queryString = queryString

        val newResult = repository.getRepositorySearchItems(queryString)
            .cachedIn(viewModelScope)
        searchResult = newResult
        return newResult
    }
}