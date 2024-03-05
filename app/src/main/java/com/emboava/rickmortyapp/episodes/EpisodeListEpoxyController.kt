package com.emboava.rickmortyapp.episodes

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.emboava.rickmortyapp.R
import com.emboava.rickmortyapp.databinding.ModelEpisodeListItemBinding
import com.emboava.rickmortyapp.domain.models.Episode
import com.emboava.rickmortyapp.epoxy.ViewBindingKotlinModel

class EpisodeListEpoxyController : PagingDataEpoxyController<Episode>() {
    override fun buildItemModel(currentPosition: Int, item: Episode?): EpoxyModel<*> {
        return EpisodeListItemEpoxyModel(
            episode = item!!,
            onClick = { episode ->
                //todo
            }
        ).id("episode_${item.id}")
    }

    data class EpisodeListItemEpoxyModel(
        val episode: Episode,
        val onClick: (Int) -> Unit
    ) : ViewBindingKotlinModel<ModelEpisodeListItemBinding>(R.layout.model_episode_list_item) {
        override fun ModelEpisodeListItemBinding.bind() {
            episodeNameTextView.text = episode.name
            episodeAirDateTextView.text = episode.airDate
            episodeNumberTextView.text = episode.episode

            root.setOnClickListener { onClick(episode.id) }
        }

    }
}
