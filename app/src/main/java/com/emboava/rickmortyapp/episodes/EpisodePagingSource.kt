package com.emboava.rickmortyapp.episodes

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.emboava.rickmortyapp.domain.models.Episode
import kotlinx.coroutines.CoroutineScope

class EpisodePagingSource(
    private val coroutineScope: CoroutineScope
): PagingSource<Int, Episode>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val pageNumber = params.key ?: 1
        val previousKey = if (pageNumber == 1) null else pageNumber -1

        // todo network call with key

        return LoadResult.Page(
            data = emptyList(),
            prevKey = previousKey,
            nextKey = pageNumber + 1 // todo clean this up with network information
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}