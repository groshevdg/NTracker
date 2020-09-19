package ru.groshevdg.data.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface NewsApiService {
    companion object {
        const val NEWS_URL = "/v2/top-headlines?country=ru&apiKey=6b342c22f66c499d8967ac3661923cd2"
    }

    @GET(NEWS_URL)
    fun getNews() : Call<ResponseBody>
}