package com.example.warmanearmory.viewmodel

import androidx.lifecycle.ViewModel
import com.example.warmanearmory.model.CharacterData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CharacterViewModel : ViewModel() {
    private val _character = MutableStateFlow<CharacterData?>(null)
    val character: StateFlow<CharacterData?> = _character

    fun setCharacter(data: CharacterData) {
        _character.value = data
    }
}