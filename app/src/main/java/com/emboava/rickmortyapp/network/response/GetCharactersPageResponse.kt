package com.emboava.rickmortyapp.network.response

data class GetCharactersPageResponse(
    val info: Info,
    val results: List<GetCharacterByIdResponse> = emptyList()
) {
    data class Info(
        val count: Int,
        val pages: Int,
        val next: String?,
        val prev: String?
    )
}