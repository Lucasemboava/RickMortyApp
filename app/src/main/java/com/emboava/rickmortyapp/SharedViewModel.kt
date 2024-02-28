package com.emboava.rickmortyapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emboava.rickmortyapp.domain.models.Character
import com.emboava.rickmortyapp.network.RickMortyCache
import kotlinx.coroutines.launch


class SharedViewModel: ViewModel() {

    private val repository = SharedRepository()

    private val _characterByIdLiveData = MutableLiveData<Character?>()
    val characterByIdLiveData: LiveData<Character?> = _characterByIdLiveData

    fun fetchCharacter(characterId: Int) {

        // Chack the cache for our character
        val cachedCharacter = RickMortyCache.characterMap[characterId]
        if(cachedCharacter != null) {
            _characterByIdLiveData.postValue(cachedCharacter)
            return
        }

        // Otherwise, we need to make the network call fo the character
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)

            _characterByIdLiveData.postValue(response)

            // Update cache if non-null char received
            response?.let {
                RickMortyCache.characterMap[characterId] = it
            }
        }
    }
}