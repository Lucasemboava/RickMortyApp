package com.emboava.rickmortyapp

import com.emboava.rickmortyapp.domain.mappers.CharacterMapper
import com.emboava.rickmortyapp.network.NetworkLayer
import com.emboava.rickmortyapp.domain.models.Character
import com.emboava.rickmortyapp.network.response.GetCharacterByIdResponse
import com.emboava.rickmortyapp.network.response.GetEpisodeByIdResponse

class SharedRepository {
    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        val networkEpisodes = getEpisodesFromCharacterResponse(request.body)

        return CharacterMapper.buildFrom(
            response = request.body,
            episodes = networkEpisodes
        )
    }

    private suspend fun getEpisodesFromCharacterResponse(
        characterResponse: GetCharacterByIdResponse
    ): List<GetEpisodeByIdResponse> {
        val episodeRange = characterResponse.episode.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }.toString()
        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)

        if (request.failed || !request.isSuccessful) {
            return emptyList()
        }

        return request.body
    }
}
