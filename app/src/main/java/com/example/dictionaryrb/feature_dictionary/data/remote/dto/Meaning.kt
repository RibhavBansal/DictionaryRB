package com.example.dictionaryrb.feature_dictionary.data.remote.dto

data class Meaning(
    val antonyms: List<Any>,
    val definitions: List<Definition>,
    val partOfSpeech: String,
    val synonyms: List<String>
)