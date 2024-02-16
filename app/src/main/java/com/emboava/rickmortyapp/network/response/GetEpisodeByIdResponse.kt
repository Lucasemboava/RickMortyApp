package com.emboava.rickmortyapp.network.response

data class GetEpisodeByIdResponse(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String

)
