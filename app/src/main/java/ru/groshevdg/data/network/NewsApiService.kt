package ru.groshevdg.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import ru.groshevdg.data.network.annotation.Xml
import ru.groshevdg.models.entity.RssNews

interface NewsApiService {

    @Xml
    @GET
    suspend fun getNews(@Url url: String) : Response<RssNews>
}