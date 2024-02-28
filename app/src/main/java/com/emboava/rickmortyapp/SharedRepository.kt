package com.emboava.rickmortyapp

import com.emboava.rickmortyapp.domain.mappers.CharacterMapper
import com.emboava.rickmortyapp.network.NetworkLayer
import com.emboava.rickmortyapp.domain.models.Character
import com.emboava.rickmortyapp.network.RickMortyCache
import com.emboava.rickmortyapp.network.response.GetCharacterByIdResponse
import com.emboava.rickmortyapp.network.response.GetEpisodeByIdResponse

class SharedRepository {
    suspend fun getCharacterById(characterId: Int): Character? {

        // Check the cache for our character
        val cachedCharacter = RickMortyCache.characterMap[characterId]
        if (cachedCharacter != null) {
            return cachedCharacter
        }

        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        val networkEpisodes = getEpisodesFromCharacterResponse(request.body)

        val character = CharacterMapper.buildFrom(
            response = request.body,
            episodes = networkEpisodes
        )

        // Update cache and return value
        RickMortyCache.characterMap[characterId] = character
        return character
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
