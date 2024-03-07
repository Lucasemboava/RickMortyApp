package com.emboava.rickmortyapp.episodes.detail

import com.airbnb.epoxy.EpoxyController
import com.emboava.rickmortyapp.R
import com.emboava.rickmortyapp.databinding.ModelCharacterListItemSquareBinding
import com.emboava.rickmortyapp.epoxy.ViewBindingKotlinModel
import com.emboava.rickmortyapp.domain.models.Character
import com.squareup.picasso.Picasso

class EpisodeDetailEpoxyController(
    private val characterList: List<Character>
) : EpoxyController() {

    override fun buildModels() {
        characterList.forEach { character ->
            CharacterEpoxyModel(
                imageUrl = character.image,
                name = character.name
            ).id(character.id).addTo(this)
        }
    }

    data class CharacterEpoxyModel(
        val imageUrl: String,
        val name: String
    ) : ViewBindingKotlinModel<ModelCharacterListItemSquareBinding>(
        R.layout.model_character_list_item_square
    ) {
        override fun ModelCharacterListItemSquareBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text = name
        }
    }
}
