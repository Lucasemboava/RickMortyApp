package com.emboava.rickmortyapp.domain.mappers

import com.emboava.rickmortyapp.domain.models.Episode
import com.emboava.rickmortyapp.network.response.GetEpisodeByIdResponse

object EpisodeMapper {

    fun buildFrom(networkEpisode: GetEpisodeByIdResponse): Episode {
        return Episode(
            id = networkEpisode.id,
            name = networkEpisode.name,
            airDate = networkEpisode.airDate,
            episode = networkEpisode.episode,
        )
    }
}
