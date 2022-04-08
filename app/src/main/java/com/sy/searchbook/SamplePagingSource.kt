package com.sy.searchbook

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sy.searchbook.model.KakaoAPI
import com.sy.searchbook.model.doc

class SamplePagingSource (
    private val service: KakaoAPI,
    private val query: String
) : PagingSource<Int, doc>() {
    override fun getRefreshKey(state: PagingState<Int, doc>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, doc> {
        return try {

            val next = params.key ?: 1
            Log.i("TEST", query +" " + next)
            val response = service.getSearchBooks(query, "accuracy", next, 50)
            val items = response.documents
            //Log.i("TEST2", response.toString())

            LoadResult.Page(
                data = items,
                prevKey = if (next == 0) null else next - 1,
                nextKey = next + 1
            )
        } catch (e: Exception) {
            Log.i("TEST", "ERRR");
            Log.i("TEST", e.toString())
            LoadResult.Error(e)
        }
    }

}