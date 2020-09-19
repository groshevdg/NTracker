package ru.groshevdg.data.repository

import ru.groshevdg.data.network.NewsApiService
import ru.groshevdg.models.entity.Channel

class NewsRepository constructor(private val apiService: NewsApiService) {
    private val sources = mutableMapOf(
        "auto.rss" to true,
        "auto_racing.rss" to true,
        "army.rss" to true,
        "world.rss" to true,
        "gadgets.rss" to true,
        "communal.rss" to true,
        "health.rss" to true,
        "games.rss" to true,
        "internet.rss" to true,
        "cyber_sport.rss" to true,
        "movies.rss" to true,
        "koronavirus.rss" to true,
        "cosmos.rss" to true,
        "music.rss" to true,
        "science.rss" to true,
        "realty.rss" to true,
        "society.rss" to true,
        "politics.rss" to true,
        "incident.rss" to true,
        "travels.rss" to true,
        "religion.rss" to true,
        "sport.rss" to true,
        "computers.rss" to true,
        "vehicle.rss" to true,
        "finances.rss" to true,
        "showbusiness.rss" to true,
        "ecology.rss" to true,
        "business.rss" to true,
        "energy.rss" to true
    )

    suspend fun getNews() : List<Channel?> {
        val listOfChannels = mutableListOf<Channel?>()

        for (pair in sources) {
            if (pair.value) {
                val response = apiService.getNews("/" + pair.key)
                if (response.isSuccessful) {
                    listOfChannels.add(response.body()?.channel)
                }
            }
        }
        return listOfChannels
    }
}