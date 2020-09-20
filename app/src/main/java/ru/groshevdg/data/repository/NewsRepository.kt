package ru.groshevdg.data.repository

import ru.groshevdg.data.network.NewsApiService
import ru.groshevdg.models.entity.Channel
import ru.groshevdg.utils.ListsUtils
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: NewsApiService,
                                 private val listsUtils: ListsUtils) {

    companion object {
        const val RSS = ".rss"
    }

    var channelLoadedState = mutableMapOf<String, Boolean>()
        private set

    suspend fun getChannels() : List<Channel?> {
        val sources = listsUtils.getSourceMap()
        val listOfChannels = mutableListOf<Channel?>()
        channelLoadedState.clear()

        for (pair in sources) {
            if (pair.value) {
                val response = apiService.getNews("/" + pair.key + RSS)
                if (response.isSuccessful) {
                    listOfChannels.add(response.body()?.channel)
                    channelLoadedState[pair.key] = true
                }
                else {
                    channelLoadedState[pair.key] = false
                }
            }
        }
        return listOfChannels
    }
}