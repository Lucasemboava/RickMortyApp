package com.emboava.rickmortyapp.episodes

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.emboava.rickmortyapp.domain.mappers.EpisodeMapper
import com.emboava.rickmortyapp.domain.models.Episode
import com.emboava.rickmortyapp.network.NetworkLayer

class EpisodePagingSource(
    private val repository: EpisodeRepository
) : PagingSource<Int, Episode>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        val pageNumber = params.key ?: 1
        val previousKey = if (pageNumber == 1) null else pageNumber - 1

        val pageRequest =
            NetworkLayer.apiClient.getEpisodesPage(pageNumber) // pageNumber is next page

        pageRequest.exception?.let {
            return LoadResult.Error(it)
        }

        return LoadResult.Page(
            data = pageRequest.body.results.map { EpisodeMapper.buildFrom(it) },
            prevKey = previousKey,
            nextKey = getPageIndexFromNext(pageRequest.body.info.next)
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun getPageIndexFromNext(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }
}