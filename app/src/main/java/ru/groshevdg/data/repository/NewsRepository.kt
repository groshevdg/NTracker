package ru.groshevdg.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.groshevdg.data.network.NewsApiService
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: NewsApiService) {

    fun getNews() {
        GlobalScope.launch(Dispatchers.IO) {
            apiService.getNews().enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                }
            });
        }
    }
}