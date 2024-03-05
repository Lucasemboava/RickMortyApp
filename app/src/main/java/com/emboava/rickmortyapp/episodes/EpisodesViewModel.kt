package com.emboava.rickmortyapp.episodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.emboava.rickmortyapp.Constants.PAGE_SIZE
import com.emboava.rickmortyapp.Constants.PREFETCH_DISTANCE

class EpisodesViewModel : ViewModel() {
    private val repository = EpisodeRepository()

    val flow = Pager(
        PagingConfig(
            pageSize = PAGE_SIZE,
            prefetchDistance = PREFETCH_DISTANCE,
            enablePlaceholders = false
        )
    ) {
        EpisodePagingSource(repository)
    }.flow.cachedIn(viewModelScope)


}