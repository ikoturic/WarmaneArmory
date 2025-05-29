package com.example.warmanearmory.model

import com.google.gson.annotations.SerializedName

data class CharacterData(
    val name: String,
    val realm: String,
    val online: Boolean,
    val level: String,
    val faction: String,
    val gender: String,
    val race: String,

    @SerializedName("class")
    val className: String,

    val honorablekills: String,
    val guild: String,
    val achievementpoints: String,
    val equipment: List<EquipmentItem>,
    val talents: List<TalentTree>,
    val professions: List<Profession>
)

data class EquipmentItem(
    val name: String,
    val item: String,
    val transmog: String
)

data class TalentTree(
    val tree: String,
    val points: List<Int>
)

data class Profession(
    val name: String,
    val skill: String
)