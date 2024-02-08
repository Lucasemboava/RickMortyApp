package com.emboava.rickmortyapp.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.emboava.rickmortyapp.Constants.PAGE_SIZE
import com.emboava.rickmortyapp.Constants.PREFETCH_DISTANCE
import com.emboava.rickmortyapp.network.response.GetCharacterByIdResponse

class CharactersViewModel: ViewModel() {

    private val repository = CharactersRepository()

    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(PREFETCH_DISTANCE)
        .build()

    private val dataSourceFactory = CharactersDataSourceFactory(viewModelScope, repository)
    val charactersPagedListLiveData: LiveData<PagedList<GetCharacterByIdResponse>> =
        LivePagedListBuilder(
            dataSourceFactory,
            pageListConfig
        ).build()
}
