package com.emboava.rickmortyapp

import com.emboava.rickmortyapp.domain.mappers.CharacterMapper
import com.emboava.rickmortyapp.network.NetworkLayer
import com.emboava.rickmortyapp.domain.models.Character

class SharedRepository {
    suspend fun getCharacterById(characterId: Int): Character? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed) {
            return null
        }

        if (!request.isSuccessful) {
            return null
        }

        return CharacterMapper.buildFrom(response = request.body)
    }
}
