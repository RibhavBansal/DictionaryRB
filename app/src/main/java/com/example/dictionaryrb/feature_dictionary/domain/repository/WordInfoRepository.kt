package com.example.dictionaryrb.feature_dictionary.domain.repository

import com.example.dictionaryrb.util.Resource
import com.example.dictionaryrb.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {
    fun getWordInfo(word : String): Flow<Resource<List<WordInfo>>>
}