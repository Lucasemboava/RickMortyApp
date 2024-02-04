package com.emboava.rickmortyapp

import com.emboava.rickmortyapp.network.NetworkLayer
import com.emboava.rickmortyapp.network.response.GetCharacterByIdResponse

class SharedRepository {
    suspend fun getCharacterById(characterId: Int): GetCharacterByIdResponse? {
        val request = NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed) {
            return null
        }

        if (!request.isSuccessful) {
            return null
        }

        return request.body
    }
}
