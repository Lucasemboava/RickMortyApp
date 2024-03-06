package com.emboava.rickmortyapp.episodes

import com.emboava.rickmortyapp.domain.mappers.EpisodeMapper
import com.emboava.rickmortyapp.domain.models.Episode
import com.emboava.rickmortyapp.network.NetworkLayer
import com.emboava.rickmortyapp.network.response.GetCharacterByIdResponse
import com.emboava.rickmortyapp.network.response.GetEpisodeByIdResponse
import com.emboava.rickmortyapp.network.response.GetEpisodesPageResponse

class EpisodeRepository {

    suspend fun fetchEpisodePage(pageIndex: Int): GetEpisodesPageResponse? {

        val pageRequest = NetworkLayer.apiClient.getEpisodesPage(pageIndex)

        if (!pageRequest.isSuccessful) {
            return null
        }

        return pageRequest.body
    }

    suspend fun getEpisodeById(episodeId: Int): Episode? {
        val request = NetworkLayer.apiClient.getEpisodeById(episodeId)

        if (!request.isSuccessful) {
            return null
        }

        val characterList = getCharactersFromEpisodeResponse(request.body)
        return EpisodeMapper.buildFrom(
            networkEpisode = request.body,
            networkCharacters = characterList
        )
    }

    private suspend fun getCharactersFromEpisodeResponse(
        episodeByIdResponse: GetEpisodeByIdResponse
    ): List<GetCharacterByIdResponse> {

        val characterList = episodeByIdResponse.characters.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }

        val request = NetworkLayer.apiClient.getMultipleCharacters(characterList)
        return  request.bodyNullable ?: emptyList()
    }
}
