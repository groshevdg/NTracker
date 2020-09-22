package ru.groshevdg.usecase

import ru.groshevdg.data.repository.NewsRepository
import ru.groshevdg.models.usecase.NewItem
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun getNews() : List<NewItem> {
        val news = mutableListOf<NewItem>()
        val channelList = newsRepository.getChannels()
        val channelLoadedState = newsRepository.channelLoadedState

        for (pair in channelLoadedState) {
            news.add(NewItem.Channel(title = pair.key, isChannelLoaded = pair.value))
        }

        for (channel in channelList) {
            for (new in channel?.items ?: emptyList()) {
                news.add(NewItem.New(
                    title = new.title,
                    description = new.description,
                    category = formatCategory(channel?.title),
                    link = new.link,
                    publishDate = formatDate(new.publishDate)
                ))
            }
        }
        return news
    }

    private fun formatDate(value: String?) : String {
        return try {
            val date = SimpleDateFormat("dd MMM yyyy hh:mm:ss Z", Locale.ENGLISH).parse(value ?: "")
            SimpleDateFormat("dd MMM yyyy, hh:mm").format(date ?: Date())
        } catch (e: Exception) {
            value ?: ""
        }
    }

    private fun formatCategory(value: String?) : String {
        val position = value?.indexOf(" ")
        return "Категория:" + value?.substring(position ?: 0)
    }
}