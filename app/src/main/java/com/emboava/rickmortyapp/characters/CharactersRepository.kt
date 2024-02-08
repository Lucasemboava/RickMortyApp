package com.emboava.rickmortyapp.characters

import com.emboava.rickmortyapp.network.response.GetCharacterByIdResponse

class CharactersRepository {

    suspend fun getCharacterList(pageIndex: Int): List<GetCharacterByIdResponse> {
        return emptyList()
    }
}