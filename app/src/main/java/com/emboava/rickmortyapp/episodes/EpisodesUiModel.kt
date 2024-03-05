package com.emboava.rickmortyapp.episodes

import com.emboava.rickmortyapp.domain.models.Episode

sealed class EpisodesUiModel {
    class Item(val episode: Episode) : EpisodesUiModel()
    class  Header(val text: String) : EpisodesUiModel()
}