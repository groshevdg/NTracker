package ru.groshevdg.usecase

import ru.groshevdg.data.repository.NewsRepository
import ru.groshevdg.models.usecase.New
import javax.inject.Inject

class NewsUseCase @Inject constructor(private val newsRepository: NewsRepository) {
    suspend fun getNews() : List<New> {
        val news = mutableListOf<New>()
        val channelList = newsRepository.getChannels()
        for (channel in channelList) {
            for (new in channel?.items ?: emptyList()) {
                news.add(New(
                    title = new.title,
                    description = new.description,
                    category = channel?.title,
                    link = new.link,
                    publishDate = new.publishDate
                ))
            }
        }
        return news
    }
}