package com.emboava.rickmortyapp.episodes

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging3.PagingDataEpoxyController
import com.emboava.rickmortyapp.R
import com.emboava.rickmortyapp.databinding.ModelEpisodeListItemBinding
import com.emboava.rickmortyapp.databinding.ModelEpisodeListTitleBinding
import com.emboava.rickmortyapp.domain.models.Episode
import com.emboava.rickmortyapp.epoxy.ViewBindingKotlinModel

class EpisodeListEpoxyController : PagingDataEpoxyController<EpisodesUiModel>() {
    override fun buildItemModel(currentPosition: Int, item: EpisodesUiModel?): EpoxyModel<*> {
        return when(item!!) {
            is EpisodesUiModel.Item ->{
                val episode = (item as EpisodesUiModel.Item).episode
                EpisodeListItemEpoxyModel(
                    episode = episode,
                    onClick = { episodeId ->
                        //todo
                    }
                ).id("episode_${episode.id}")
            }

            is  EpisodesUiModel.Header -> {
                val headerText = (item as EpisodesUiModel.Header).text
                EpisodeListTitleEpoxyModel(headerText).id("header_$headerText")
            }
        }
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

    data class EpisodeListTitleEpoxyModel(
        val title: String
    ) : ViewBindingKotlinModel<ModelEpisodeListTitleBinding>(R.layout.model_episode_list_title) {
        override fun ModelEpisodeListTitleBinding.bind() {
            textView.text = title
        }
    }
}
