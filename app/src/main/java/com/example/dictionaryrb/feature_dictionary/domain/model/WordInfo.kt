package com.example.dictionaryrb.feature_dictionary.domain.model

import com.example.dictionaryrb.feature_dictionary.domain.model.Meaning

data class WordInfo(
    val meanings: List<Meaning>,
    val phonetic: String,
    val word: String
)
