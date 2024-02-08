package com.emboava.rickmortyapp.characters

import com.emboava.rickmortyapp.network.NetworkLayer
import com.emboava.rickmortyapp.network.response.GetCharactersPageResponse

class CharactersRepository {

    suspend fun getCharacterPage(pageIndex: Int): GetCharactersPageResponse? {
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful) {
            return null
        }

        return request.body
    }
}