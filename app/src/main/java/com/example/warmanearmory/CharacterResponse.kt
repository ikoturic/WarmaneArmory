package com.example.warmanearmory.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    val name: String,
    val race: String,
    val gender: String,

    @SerializedName("equipment")
    val equipment: List<EquipmentResponse>
)

data class EquipmentResponse(
    val item: String,
    val name: String
)