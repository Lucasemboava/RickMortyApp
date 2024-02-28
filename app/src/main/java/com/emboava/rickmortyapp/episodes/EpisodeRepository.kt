package com.emboava.rickmortyapp.episodes

import com.emboava.rickmortyapp.network.NetworkLayer
import com.emboava.rickmortyapp.network.response.GetEpisodesPageResponse

class EpisodeRepository {

    suspend fun fetchEpisodePage(pageIndex: Int): GetEpisodesPageResponse? {

        val pageRequest = NetworkLayer.apiClient.getEpisodesPage(pageIndex)

        if (!pageRequest.isSuccessful) {
            return null
        }

        return pageRequest.body
    }
}
