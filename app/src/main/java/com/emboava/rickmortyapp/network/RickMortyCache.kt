package com.emboava.rickmortyapp.network

import com.emboava.rickmortyapp.domain.models.Character

object RickMortyCache {

    val characterMap = mutableMapOf<Int, Character>()

}
