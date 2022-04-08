package com.sy.searchbook

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sy.searchbook.model.KakaoAPI
import com.sy.searchbook.model.doc
import kotlinx.coroutines.flow.Flow

class PagingRepository (
    private val service: KakaoAPI
) {
    fun getRepositorySearchItems(query: String): Flow<PagingData<doc>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SamplePagingSource(service, query) }
        ).flow
    }
}