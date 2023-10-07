package com.example.dictionaryrb.feature_dictionary.data.repository

import com.example.dictionaryrb.util.Resource
import com.example.dictionaryrb.feature_dictionary.data.local.WordInfoDao
import com.example.dictionaryrb.feature_dictionary.data.remote.DictApi
import com.example.dictionaryrb.feature_dictionary.domain.model.WordInfo
import com.example.dictionaryrb.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException

class WordInfoRepositoryImpl(
    private val api: DictApi,
    private val dao: WordInfoDao
): WordInfoRepository {

    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordInfos))

        try {
            val remoteWordInfos = api.getWordInfo(word)
            dao.deleteWordInfos(remoteWordInfos.map { it.word })
            dao.insertWordInfos(remoteWordInfos.map { it.toWordInfoEntity() })
        } catch (e : HttpException) {
            emit(
                Resource.Error(
                message = "oops, try again",
                data = wordInfos
            ))
        } catch (e : IOException) {
            emit(
                Resource.Error(
                message = "Couldn't reach server, check your internet connection",
                data = wordInfos
            ))
        }

        val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
        emit(Resource.Success(newWordInfos))
    }
}